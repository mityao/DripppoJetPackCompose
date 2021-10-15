package com.wyao.dribbbojetpackcompose.data.remote

import com.wyao.dribbbojetpackcompose.Shot
import com.wyao.dribbbojetpackcompose.User
import retrofit2.http.*

interface DribbboApi {

    @GET("user")
    suspend fun getUser(): User

    @GET("user/shots")
    suspend fun getShots(): List<Shot>

    @GET("user/shots/{id}")
    suspend fun getShot(@Path("id") id: String): Shot
}