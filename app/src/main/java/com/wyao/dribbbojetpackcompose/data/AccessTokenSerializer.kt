package com.wyao.dribbbojetpackcompose.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.wyao.dribbbojetpackcompose.AccessToken
import java.io.InputStream
import java.io.OutputStream

object AccessTokenSerializer : Serializer<AccessToken> {
    override val defaultValue: AccessToken = AccessToken.getDefaultInstance()

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun readFrom(input: InputStream): AccessToken {
        try {
            return AccessToken.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: AccessToken, output: OutputStream) = t.writeTo(output)
}