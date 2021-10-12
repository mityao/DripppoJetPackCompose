package com.wyao.dribbbojetpackcompose.prefsstore

import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    suspend fun storeAccessToken(accessToken: AccessTokenDto)

    suspend fun getAccessToken(): Flow<AccessToken>

}