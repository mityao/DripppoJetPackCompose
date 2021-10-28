package com.wyao.dribbbojetpackcompose.presentation.login

import com.wyao.dribbbojetpackcompose.data.remote.dto.UserDto

data class LoginState(
    val isLoading: Boolean = false,
    val user: UserDto? = null,
    val error: String = ""
)
