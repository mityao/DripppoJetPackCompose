package com.wyao.dribbbojetpackcompose.data

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("client_id")
    var clientId: String,

    @SerializedName("client_secret")
    var clientSecret: String,

    @SerializedName("code")
    var code: String,

    @SerializedName("redirect_uri")
    var redirectUri: String
)


