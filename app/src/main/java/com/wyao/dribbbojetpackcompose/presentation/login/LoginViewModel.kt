package com.wyao.dribbbojetpackcompose.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wyao.dribbbojetpackcompose.common.Constants
import com.wyao.dribbbojetpackcompose.common.Result
import com.wyao.dribbbojetpackcompose.data.AuthorizationInterceptor
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import com.wyao.dribbbojetpackcompose.data.remote.dto.UserDto
import com.wyao.dribbbojetpackcompose.data.remote.dto.toAccessToken
import com.wyao.dribbbojetpackcompose.domain.repository.AuthRepository
import com.wyao.dribbbojetpackcompose.domain.repository.DribbboRepository
import com.wyao.dribbbojetpackcompose.domain.use_case.get_access_token.GetAccessTokenUseCase
import com.wyao.dribbbojetpackcompose.domain.use_case.user_login.UserLoginUseCase
import com.wyao.dribbbojetpackcompose.presentation.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val dribbboRepository: DribbboRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    init {
        Log.d("WEI", "LoginViewModel init")
        if (savedStateHandle.get<String>(Constants.ACCESS_TOKEN)?.equals("true") == true) {
            Log.d("WEI", "LoginViewModel logIn")
            logIn()
        }
    }

    fun logIn() {
        userLoginUseCase().onEach { result ->
            when (result) {
                is Result.Success -> {
                    Log.d("WEI", "LoginViewModel $result.data.name")
                    _state.value = LoginState(user = result.data)
                    storeUser(result.data)
                }
                is Result.Loading -> {
                    Log.d("WEI", "LoginViewModel Loading")
                    _state.value = LoginState(isLoading = true)
                }
                is Result.Error -> {
                    _state.value = LoginState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun storeUser(userDto: UserDto?) {
        if (userDto != null) {
            dribbboRepository.storeUser(userDto)
        }
    }
}