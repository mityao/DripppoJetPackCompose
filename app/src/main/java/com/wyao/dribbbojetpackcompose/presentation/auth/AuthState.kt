package com.wyao.dribbbojetpackcompose.presentation.auth

import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto

data class AuthState(
    val isLoading: Boolean = false,
    val accessTokenDto: AccessTokenDto? = null,
    val error: String = ""
)