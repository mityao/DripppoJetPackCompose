package com.wyao.dribbbojetpackcompose.presentation.auth

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wyao.dribbbojetpackcompose.common.Result
import com.wyao.dribbbojetpackcompose.data.AuthorizationInterceptor
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import com.wyao.dribbbojetpackcompose.domain.repository.AuthRepository
import com.wyao.dribbbojetpackcompose.domain.use_case.get_access_token.GetAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authorizationInterceptor: AuthorizationInterceptor,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
): ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state
    var accessTokenString: String? = null

    fun getRedirectUri(): String {
        return authRepository.getRedirectUri()
    }

    fun getKeyCode(): String {
        return authRepository.getKeyCode()
    }

    fun getAuthorizeUrl(): String {
        return authRepository.getAuthorizeUrl()
    }

    fun fetchAccessToken(accessCode: String) {
        getAccessTokenUseCase(accessCode).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _state.value = AuthState(accessTokenDto = result.data)
                    Log.d("WEI", "AuthViewModel AuthState accessTokenDto")
                    updateAccessTokenForAuthorizationInterceptor(result.data?.access_token)
                    storeAccessToken(result.data)
                }
                is Result.Loading -> {
                    Log.d("WEI", "AuthViewModel AuthState Loading")
                    _state.value = AuthState(isLoading = true)
                }
                is Result.Error -> {
                    _state.value = AuthState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun storeAccessToken(accessTokenDto: AccessTokenDto?) {
        if (accessTokenDto != null) {
            authRepository.storeAccessToken(accessTokenDto)
        }
    }

    private fun updateAccessTokenForAuthorizationInterceptor(accessTokenString: String?) {
        if (accessTokenString != null) {
            authorizationInterceptor.setAccessToken(accessTokenString)
        }
    }

    fun getUpdateAccessTokenString(): String? {
        return accessTokenString
    }

    fun updateAccessTokenString() {
        accessTokenString = state.value.accessTokenDto?.access_token
    }

}