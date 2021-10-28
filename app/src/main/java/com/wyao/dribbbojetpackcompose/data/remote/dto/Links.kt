package com.wyao.dribbbojetpackcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("twitter")
    val twitter: String,

    @SerializedName("web")
    val web: String
)