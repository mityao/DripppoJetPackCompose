package com.wyao.dribbbojetpackcompose.domain.repository

import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun getKeyCode(): String

    fun getRedirectUri(): String

    suspend fun fetchAccessToken(authCode: String): AccessTokenDto

    suspend fun loadAccessToken(): Flow<AccessToken>

    suspend fun storeAccessToken(accessTokenDto: AccessTokenDto)

    fun getAuthorizeUrl(): String
}