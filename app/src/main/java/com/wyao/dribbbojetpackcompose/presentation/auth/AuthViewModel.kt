package com.wyao.dribbbojetpackcompose.presentation.auth

import androidx.lifecycle.ViewModel
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.data.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl): ViewModel() {

    fun getRedirectUri(): String {
        return authRepositoryImpl.getRedirectUri()
    }

    fun getKeyCode(): String {
        return authRepositoryImpl.getKeyCode()
    }

    fun getAuthorizeUrl(): String {
        return authRepositoryImpl.getAuthorizeUrl()
    }

    suspend fun fetchAccessToken(accessCode: String): AccessToken {
        return authRepositoryImpl.fetchAccessToken(accessCode)
    }

}