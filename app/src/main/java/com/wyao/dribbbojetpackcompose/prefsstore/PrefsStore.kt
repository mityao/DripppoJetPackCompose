package com.wyao.dribbbojetpackcompose.prefsstore

import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.User
import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    suspend fun storeAccessToken(accessToken: AccessToken)

    suspend fun loadAccessToken(): Flow<AccessToken>

    suspend fun storeUser(user: User)

    suspend fun loadUser(): Flow<User>

}