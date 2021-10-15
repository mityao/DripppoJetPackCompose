package com.wyao.dribbbojetpackcompose.data.repository

import android.net.Uri
import com.wyao.dribbbojetpackcompose.AccessToken
import com.wyao.dribbbojetpackcompose.data.remote.AuthApi
import com.wyao.dribbbojetpackcompose.di.IoDispatcher
import com.wyao.dribbbojetpackcompose.domain.repository.AuthRepository
import com.wyao.dribbbojetpackcompose.prefsstore.PrefsStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val prefsStore: PrefsStore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): AuthRepository {

    val REQ_CODE = 100

    private val KEY_CODE = "code"
    private val KEY_CLIENT_ID = "client_id"
    private val KEY_CLIENT_SECRET = "client_secret"
    private val KEY_REDIRECT_URI = "redirect_uri"
    private val KEY_SCOPE = "scope"
    private val KEY_ACCESS_TOKEN = "access_token"

    private val CLIENT_ID = "c63438c61687b69749f81253d5af6f70d197daba49a96abca63f6b800c2c44e1"

    private val CLIENT_SECRET = "ebe0e82f0157a1d65e1abfd1bc4fb04f9f0a5866106a8bce04b480d675490191"

    private val SCOPE = "public+upload"

    private val URI_AUTHORIZE = "https://dribbble.com/oauth/authorize"

    private val REDIRECT_URI = "http://www.baidu.com/more"

    override fun getKeyCode(): String {
        return KEY_CODE
    }

    override fun getRedirectUri(): String {
        return REDIRECT_URI
    }

    override suspend fun fetchAccessToken(authCode: String): AccessToken {
        return withContext(ioDispatcher) {
            val postBody: RequestBody = MultipartBody.Builder()
                .addFormDataPart(KEY_CLIENT_ID, CLIENT_ID)
                .addFormDataPart(KEY_CLIENT_SECRET, CLIENT_SECRET)
                .addFormDataPart(KEY_CODE, authCode)
                .addFormDataPart(KEY_REDIRECT_URI, REDIRECT_URI)
                .build()
            authApi.fetchAccessToken(postBody)
        }
    }

    override suspend fun loadAccessToken(): Flow<AccessToken> {
        return withContext(ioDispatcher) {
            prefsStore.loadAccessToken()
        }
    }

    override suspend fun storeAccessToken(accessToken: AccessToken) {
        withContext(ioDispatcher) {
            prefsStore.storeAccessToken(accessToken)
        }
    }

    override fun getAuthorizeUrl(): String {
        var url = Uri.parse(URI_AUTHORIZE)
            .buildUpon()
            .appendQueryParameter(KEY_CLIENT_ID, CLIENT_ID)
            .build()
            .toString()
        url += "&$KEY_REDIRECT_URI=$REDIRECT_URI"
        url += "&$KEY_SCOPE=$SCOPE"
        return url
    }

}