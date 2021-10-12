package com.wyao.dribbbojetpackcompose.dribbbo

import android.content.Context
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.data.remote.DribbboApi
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import com.wyao.dribbbojetpackcompose.prefsstore.PrefsStore
import javax.inject.Inject

class DribbboImpl @Inject constructor(
    private val dribbboApi: DribbboApi,
    private val prefsStore: PrefsStore
    ): Dribbbo {

    init {
        // Check if there's AccessToken stored already
        accessToken = loadAccessToken().accessToken
    }

    private val accessToken: String? = null

    override fun isLoggedIn(): Boolean {
        return accessToken != null
    }

    override suspend fun login(accessToken: AccessTokenDto) {

    }

    override suspend fun logOut(context: Context) {
        TODO("Not yet implemented")
    }

    override suspend fun loadAccessToken(): AccessToken {
        return prefsStore.getAccessToken()
    }

    override suspend fun saveAccessToken(accessTokenDto: AccessTokenDto) {
        prefsStore.storeAccessToken(accessTokenDto)
    }
}