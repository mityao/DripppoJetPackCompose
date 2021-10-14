package com.wyao.dribbbojetpackcompose.data.remote

import com.wyao.dribbbojetpackcompose.User
import okhttp3.RequestBody
import retrofit2.http.*

interface DribbboApi {

    @GET("user")
    fun getUser(): User
}