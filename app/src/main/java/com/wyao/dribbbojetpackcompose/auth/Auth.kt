package com.wyao.dribbbojetpackcompose.auth

import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto

interface Auth {

    fun getKeyCode(): String

    fun getRedirectUri(): String

    suspend fun fetchAccessToken(authCode: String): AccessTokenDto

    fun getAuthorizeUrl(): String
}