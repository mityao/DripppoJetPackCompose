package com.wyao.dribbbojetpackcompose.presentation.auth

import androidx.lifecycle.ViewModel
import com.wyao.dribbbojetpackcompose.auth.Auth
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val auth: Auth): ViewModel() {

    val authUrl = auth.getAuthorizeUrl()
    val redirectUrl = auth.getRedirectUri()
    val keyCode = auth.getKeyCode()

    suspend fun fetchAccessToken(accessCode: String): AccessTokenDto {
        return auth.fetchAccessToken(accessCode)
    }

}