package com.wyao.dribbbojetpackcompose.prefsstore

import com.wyao.dribbbojetpackcompose.AccessToken
import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    suspend fun storeAccessToken(accessToken: AccessToken)

    suspend fun loadAccessToken(): Flow<AccessToken>

}