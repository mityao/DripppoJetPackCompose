package com.wyao.dribbbojetpackcompose.presentation.login

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wyao.dribbbojetpackcompose.common.Constants
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import com.wyao.dribbbojetpackcompose.dribbbo.Dribbbo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dribbbo: Dribbbo,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    init {
        savedStateHandle.get<AccessTokenDto>(Constants.ACCESS_TOKEN)?.let { accessToken ->
            viewModelScope.launch {
                logIn(accessToken)
            }
        }
    }

    fun isLoggedIn(): Boolean {
        return dribbbo.isLoggedIn()
    }

    suspend fun logIn(accessTokenDto: AccessTokenDto) {
        dribbbo.login(accessTokenDto)
    }
}