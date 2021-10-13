package com.wyao.dribbbojetpackcompose.presentation.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.common.Constants
import com.wyao.dribbbojetpackcompose.domain.repository.DribbboRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dribbboRepository: DribbboRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    init {
        savedStateHandle.get<AccessToken>(Constants.ACCESS_TOKEN)?.let { accessToken ->
            viewModelScope.launch {
                logIn(accessToken)
            }
        }
    }

    fun isLoggedIn(): Boolean {
        return dribbboRepository.isLoggedIn()
    }

    fun logIn(AccessToken: AccessToken) {
        viewModelScope.launch {
            dribbboRepository.login(AccessToken)
        }
    }
}