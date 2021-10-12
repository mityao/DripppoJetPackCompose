package com.wyao.dribbbojetpackcompose.data.remote

import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @GET("oauth/authorize")
    suspend fun getAuthorization()

    @POST("oauth/token")
    suspend fun fetchAccessToken(@Body body: RequestBody): AccessTokenDto
}