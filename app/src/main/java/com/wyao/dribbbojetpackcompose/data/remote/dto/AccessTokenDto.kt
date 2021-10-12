package com.wyao.dribbbojetpackcompose.data.remote.dto

data class AccessTokenDto(
    val access_token: String,
    val scope: String,
    val token_type: String
)
