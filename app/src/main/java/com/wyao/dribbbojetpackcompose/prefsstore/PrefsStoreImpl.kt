package com.wyao.dribbbojetpackcompose.prefsstore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.data.AccessTokenSerializer
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

private const val AUTH_TOKEN_STORE_NAME = "access_token_data_store"
private const val AUTH_TOKEN__NAME = "access_token"

class PrefsStoreImpl @Inject constructor(@ApplicationContext context: Context) : PrefsStore {

    private val TAG = "PrefsStoreImpl"
    private val mContext = context

    val ACCESS_TOKEN = stringPreferencesKey(AUTH_TOKEN__NAME)

    override suspend fun storeAccessToken(accessTokenDto: AccessTokenDto) {
        mContext.authTokenDataStore.updateData { preferences ->
            preferences.toBuilder()
                .setAccessToken(accessTokenDto.access_token)
                .setTokenType(accessTokenDto.token_type)
                .setScope(accessTokenDto.scope)
                .build()
        }
    }

    override suspend fun getAccessToken(): Flow<AccessToken> {
        return accessTokenFlow
    }

    // At the top level of your kotlin file:
    private val Context.authTokenDataStore: DataStore<AccessToken> by dataStore(
        fileName = AUTH_TOKEN_STORE_NAME,
        serializer = AccessTokenSerializer
    )

    // Read AccessToken from DataStore
    private val accessTokenFlow: Flow<AccessToken> = mContext.authTokenDataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading sort order preferences.", exception)
                emit(AccessToken.getDefaultInstance())
            } else {
                throw exception
            }
        }

}