package com.wyao.dribbbojetpackcompose.data.remote

import com.wyao.dribbbojetpackcompose.data.AuthRequest
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("oauth/token")
    suspend fun fetchAccessToken(@Body body: AuthRequest): AccessTokenDto
}