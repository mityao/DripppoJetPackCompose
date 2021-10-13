package com.wyao.dribbbojetpackcompose.data.repository

import android.content.Context
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.domain.repository.AuthRepository
import com.wyao.dribbbojetpackcompose.domain.repository.DribbboRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DribbboRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository
) : DribbboRepository {

    private var _accessToken: String? = null

    override suspend fun init() {
        authRepository.loadAccessToken().collect {
            _accessToken = it.accessToken
        }
        if (_accessToken != null) {
            // User already exist, load it
        }
    }

    override fun isLoggedIn(): Boolean {
        return _accessToken != null
    }

    override suspend fun login(accessToken: AccessToken) {

    }

    override suspend fun logOut(context: Context) {
        TODO("Not yet implemented")
    }

    override suspend fun storeAccessToken(accessToken: AccessToken) {
        authRepository.storeAccessToken(accessToken)
    }
}