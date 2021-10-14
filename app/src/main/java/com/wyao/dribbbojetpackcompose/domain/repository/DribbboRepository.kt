package com.wyao.dribbbojetpackcompose.domain.repository

import android.content.Context
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.User

interface DribbboRepository {

    fun isLoggedIn(): Boolean

    suspend fun init()

    suspend fun login(accessToken: AccessToken)

    suspend fun logOut(context: Context)

    fun getUser(): User?

}