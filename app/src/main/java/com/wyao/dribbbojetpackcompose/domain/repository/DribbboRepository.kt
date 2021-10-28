package com.wyao.dribbbojetpackcompose.domain.repository

import android.content.Context
import com.wyao.dribbbojetpackcompose.User
import com.wyao.dribbbojetpackcompose.data.remote.dto.UserDto

interface DribbboRepository {

    fun isLoggedIn(): Boolean

    suspend fun init()

    suspend fun login(): UserDto

    suspend fun logOut(context: Context)

    fun getUser(): User?

    suspend fun storeUser(userDto: UserDto)

}