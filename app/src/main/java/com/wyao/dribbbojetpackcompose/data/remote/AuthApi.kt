package com.wyao.dribbbojetpackcompose.data.remote

import com.wyao.dribbbojetpackcompose.AccessToken
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("oauth/token")
    suspend fun fetchAccessToken(@Body body: RequestBody): AccessToken
}