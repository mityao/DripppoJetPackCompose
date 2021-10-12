package com.wyao.dribbbojetpackcompose.di

import com.wyao.dribbbojetpackcompose.common.Constants
import com.wyao.dribbbojetpackcompose.data.remote.DribbboApi
import com.wyao.dribbbojetpackcompose.dribbbo.Dribbbo
import com.wyao.dribbbojetpackcompose.dribbbo.DribbboImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideFetchAccessTokenApi(): DribbboApi {
        return Retrofit.Builder()
            .baseUrl(Constants.AUTHORIZE_BASE_URL)
            .build()
            .create(DribbboApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDeibbbo(): Dribbbo {
        return DribbboImpl(provideFetchAccessTokenApi())
    }
}