package com.wyao.dribbbojetpackcompose.data.repository

import android.content.Context
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.User
import com.wyao.dribbbojetpackcompose.data.AuthorizationInterceptor
import com.wyao.dribbbojetpackcompose.data.remote.DribbboApi
import com.wyao.dribbbojetpackcompose.di.IoDispatcher
import com.wyao.dribbbojetpackcompose.domain.repository.AuthRepository
import com.wyao.dribbbojetpackcompose.domain.repository.DribbboRepository
import com.wyao.dribbbojetpackcompose.prefsstore.PrefsStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DribbboRepositoryImpl @Inject constructor(
    private val dribbboApi: DribbboApi,
    private val prefsStore: PrefsStore,
    private val authRepository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : DribbboRepository {

    private var _accessToken: String? = null
    private var _user: User? = null

    override suspend fun init() {
        authRepository.loadAccessToken().collect {
            _accessToken = it.accessToken
        }
        if (_accessToken != null) {
            // User already exist, load it
        } else {

        }
    }

    override fun isLoggedIn(): Boolean {
        return _accessToken != null
    }

    override suspend fun login(accessToken: AccessToken) {
        withContext(ioDispatcher) {
            _accessToken = accessToken.accessToken
            authRepository.storeAccessToken(accessToken)
            _user = fetchUser()
            _user?.let { storeUser(it) }
        }
    }

    override suspend fun logOut(context: Context) {
        TODO("Not yet implemented")
    }

    override fun getUser(): User? {
        return _user
    }

    private suspend fun fetchUser(): User {
        return withContext(ioDispatcher) {
            dribbboApi.getUser()
        }
    }

    private suspend fun storeUser(user: User) {
        prefsStore.storeUser(user)
    }

}