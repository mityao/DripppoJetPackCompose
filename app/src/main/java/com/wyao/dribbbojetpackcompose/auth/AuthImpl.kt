package com.wyao.dribbbojetpackcompose.auth

import android.net.Uri
import com.wyao.dribbbojetpackcompose.data.remote.AuthApi
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import com.wyao.dribbbojetpackcompose.prefsstore.PrefsStore
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AuthImpl @Inject constructor(private val authApi: AuthApi): Auth {

    val REQ_CODE = 100

    private val KEY_CODE = "code"
    private val KEY_CLIENT_ID = "client_id"
    private val KEY_CLIENT_SECRET = "client_secret"
    private val KEY_REDIRECT_URI = "redirect_uri"
    private val KEY_SCOPE = "scope"
    private val KEY_ACCESS_TOKEN = "access_token"

    private val CLIENT_ID = "e50e131a8baf0b6a0fbca9afac17904696780962ec12358dfbd25cd139f6e092"

    private val CLIENT_SECRET = "804c7e22ef61e48bc317204bca6fcb57f8e60feef610d6752d81a4b6a004b2ae"

    private val SCOPE = "public+write"

    private val URI_AUTHORIZE = "https://dribbble.com/oauth/authorize"

    private val REDIRECT_URI = "http://www.baidu.com"

    override fun getKeyCode(): String {
        return KEY_CODE
    }

    override fun getRedirectUri(): String {
        return REDIRECT_URI
    }

    override suspend fun fetchAccessToken(authCode: String): AccessTokenDto {
        val postBody: RequestBody = MultipartBody.Builder()
            .addFormDataPart(KEY_CLIENT_ID, CLIENT_ID)
            .addFormDataPart(KEY_CLIENT_SECRET, CLIENT_SECRET)
            .addFormDataPart(KEY_CODE, authCode)
            .addFormDataPart(KEY_REDIRECT_URI, REDIRECT_URI)
            .build()
        return authApi.fetchAccessToken(postBody)
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