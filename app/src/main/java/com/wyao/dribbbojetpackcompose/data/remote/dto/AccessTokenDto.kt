package com.wyao.dribbbojetpackcompose.data.remote.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.wyao.dribbbojetpackcompose.AccessToken

data class AccessTokenDto(
    @SerializedName("access_token")
    val access_token: String?,

    @SerializedName("scope")
    val scope: String?,

    @SerializedName("token_type")
    val token_type: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(access_token)
        parcel.writeString(scope)
        parcel.writeString(token_type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AccessTokenDto> {
        override fun createFromParcel(parcel: Parcel): AccessTokenDto {
            return AccessTokenDto(parcel)
        }

        override fun newArray(size: Int): Array<AccessTokenDto?> {
            return arrayOfNulls(size)
        }
    }
}

fun AccessTokenDto.toAccessToken(): AccessToken {
    return AccessToken.newBuilder()
        .setAccessToken(access_token)
        .setScope(scope)
        .setTokenType(token_type)
        .build()
}