package com.wyao.dribbbojetpackcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("avatar_url")
    val avatar_url: String,

    @SerializedName("bio")
    val bio: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("links")
    val links: Links,

    @SerializedName("location")
    val location: String,

    @SerializedName("login")
    val login: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("updated_at")
    val updatedAt: String
)