package com.wyao.dribbbojetpackcompose.prefsstore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.User
import com.wyao.dribbbojetpackcompose.data.AccessTokenSerializer
import com.wyao.dribbbojetpackcompose.data.AuthorizationInterceptor
import com.wyao.dribbbojetpackcompose.data.DribbboUserSerializer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

private const val AUTH_TOKEN_STORE_NAME = "access_token_data_store"
private const val DRIBBBO_USER_STORE_NAME = "dribbbo_user_data_store"

class PrefsStoreImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val authorizationInterceptor: AuthorizationInterceptor
) : PrefsStore {

    private val TAG = "PrefsStoreImpl"
    private val _context = context

    override suspend fun storeAccessToken(accessToken: AccessToken) {
        authorizationInterceptor.setAccessToken(accessToken.accessToken)
        _context.authTokenDataStore.updateData { preferences ->
            preferences.toBuilder()
                .setAccessToken(accessToken.accessToken)
                .setTokenType(accessToken.tokenType)
                .setScope(accessToken.scope)
                .build()
        }
    }

    override suspend fun loadAccessToken(): Flow<AccessToken> {
        authorizationInterceptor.setAccessToken(_accessTokenFlow.first().accessToken)
        return _accessTokenFlow
    }

    override suspend fun storeUser(user: User) {
        _context.dribbboUserDataStore.updateData { preferences ->
            preferences.toBuilder()
                .setName(user.name)
                .setAvatarUrl(user.avatarUrl)
                .build()
        }
    }

    override suspend fun loadUser(): Flow<User> {
        return _user
    }

    // At the top level of your kotlin file:
    private val Context.authTokenDataStore: DataStore<AccessToken> by dataStore(
        fileName = AUTH_TOKEN_STORE_NAME,
        serializer = AccessTokenSerializer
    )

    private val Context.dribbboUserDataStore: DataStore<User> by dataStore(
        fileName = DRIBBBO_USER_STORE_NAME,
        serializer = DribbboUserSerializer
    )

    // Read AccessToken from DataStore
    private val _accessTokenFlow: Flow<AccessToken> = _context.authTokenDataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading AccessToken preferences.", exception)
                emit(AccessToken.getDefaultInstance())
            } else {
                throw exception
            }
        }

    private val _user: Flow<User> = _context.dribbboUserDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading User preferences.", exception)
                emit(User.getDefaultInstance())
            } else {
                throw exception
            }
        }
}