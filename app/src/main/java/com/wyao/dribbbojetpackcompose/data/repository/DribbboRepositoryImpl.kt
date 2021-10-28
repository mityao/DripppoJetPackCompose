package com.wyao.dribbbojetpackcompose.data.repository

import android.content.Context
import android.util.Log
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.User
import com.wyao.dribbbojetpackcompose.data.remote.DribbboApi
import com.wyao.dribbbojetpackcompose.data.remote.dto.UserDto
import com.wyao.dribbbojetpackcompose.di.IoDispatcher
import com.wyao.dribbbojetpackcompose.domain.repository.AuthRepository
import com.wyao.dribbbojetpackcompose.domain.repository.DribbboRepository
import com.wyao.dribbbojetpackcompose.prefsstore.PrefsStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DribbboRepositoryImpl @Inject constructor(
    private val dribbboApi: DribbboApi,
    private val prefsStore: PrefsStore,
    private val authRepository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DribbboRepository {

    private val TAG: String = "DribbboRepositoryImpl"
    private var _accessToken: AccessToken? = null
    private var _user: User? = null

    override suspend fun init() {
        authRepository.loadAccessToken().collect {
            _accessToken = it
        }
        if (_accessToken != null) {
            loadUser()
            // User already exist, load it
            Log.d(TAG, "AccessToken: " + _accessToken!!.accessToken)
        } else {
            Log.d(TAG, "AccessToken not present")
        }
    }

    override fun isLoggedIn(): Boolean {
        return _accessToken != null
    }

    override suspend fun login(): UserDto {
        return withContext(ioDispatcher) {
            fetchUser()
//            storeUser(_user)
//            authRepository.loadAccessToken().collect {
//                _accessToken = it
//            }
        }
    }

    override suspend fun logOut(context: Context) {
        TODO("Not yet implemented")
    }

    override fun getUser(): User? {
        return _user
    }

    private suspend fun fetchUser(): UserDto {
        return withContext(ioDispatcher) {
            dribbboApi.getUser()
        }
    }

    private suspend fun storeUser(user: User?) {
        if (user != null) {
            prefsStore.storeUser(user)
        }
    }

    override suspend fun storeUser(userDto: UserDto) {
        val user = User.newBuilder()
                .setName(userDto.name)
                .setAvatarUrl(userDto.avatarUrl)
                .build()
        prefsStore.storeUser(user)
    }

    private suspend fun loadUser(): Flow<User> {
        return withContext(ioDispatcher) {
            prefsStore.loadUser()
        }
    }
}