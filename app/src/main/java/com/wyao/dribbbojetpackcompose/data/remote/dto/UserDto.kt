package com.wyao.dribbbojetpackcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("bio")
    val bio: String,

    @SerializedName("can_upload_shot")
    val canUploadShot: Boolean,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("followers_count")
    val followersCount: Int,

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

    @SerializedName("pro")
    val pro: Boolean,

    @SerializedName("teams")
    val teams: List<Team>,

    @SerializedName("type")
    val type: String
)