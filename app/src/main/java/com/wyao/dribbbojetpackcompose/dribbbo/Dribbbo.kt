package com.wyao.dribbbojetpackcompose.dribbbo

import android.content.Context
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto

interface Dribbbo {

    fun isLoggedIn(): Boolean

    suspend fun login(accessToken: AccessTokenDto)

    suspend fun logOut(context: Context)

    suspend fun loadAccessToken(): AccessToken

    suspend fun saveAccessToken(accessTokenDto: AccessTokenDto)

}