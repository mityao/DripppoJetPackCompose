package com.wyao.dribbbojetpackcompose.data

import com.wyao.dribbbojetpackcompose.common.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor() : Interceptor {

    private var _access_token: String? = null

    fun setAccessToken(accessToken: String) {
        _access_token = accessToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if (request.header(Constants.NO_AUTH_HEADER_KEY) == null) {
            if (_access_token == null) {
                throw RuntimeException("Session token should be defined for auth apis");
            } else {
                requestBuilder.addHeader(Constants.AUTHORIZATION_HEADER, Constants.BEARER + _access_token)
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}