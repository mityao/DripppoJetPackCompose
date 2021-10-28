package com.wyao.dribbbojetpackcompose.data.remote

import com.wyao.dribbbojetpackcompose.Shot
import com.wyao.dribbbojetpackcompose.User
import com.wyao.dribbbojetpackcompose.data.remote.dto.UserDto
import retrofit2.http.*

interface DribbboApi {

    @GET("user")
    suspend fun getUser(): UserDto

    @GET("user/shots")
    suspend fun getShots(): List<Shot>

    @GET("user/shots/{id}")
    suspend fun getShot(@Path("id") id: String): Shot
}