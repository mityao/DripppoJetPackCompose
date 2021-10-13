package com.wyao.dribbbojetpackcompose.domain.repository

import com.wyao.dribbbojetpackcompose.AccessToken
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun getKeyCode(): String

    fun getRedirectUri(): String

    suspend fun fetchAccessToken(authCode: String): AccessToken

    suspend fun loadAccessToken(): Flow<AccessToken>

    suspend fun storeAccessToken(accessToken: AccessToken)

    fun getAuthorizeUrl(): String
}