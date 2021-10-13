package com.wyao.dribbbojetpackcompose.di

import android.content.Context
import com.wyao.dribbbojetpackcompose.common.Constants
import com.wyao.dribbbojetpackcompose.data.remote.AuthApi
import com.wyao.dribbbojetpackcompose.data.repository.AuthRepositoryImpl
import com.wyao.dribbbojetpackcompose.domain.repository.AuthRepository
import com.wyao.dribbbojetpackcompose.domain.repository.DribbboRepository
import com.wyao.dribbbojetpackcompose.data.repository.DribbboRepositoryImpl
import com.wyao.dribbbojetpackcompose.prefsstore.PrefsStore
import com.wyao.dribbbojetpackcompose.prefsstore.PrefsStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(Constants.AUTHORIZE_BASE_URL)
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun providePrefsStore(@ApplicationContext context: Context): PrefsStore {
        return PrefsStoreImpl(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefsStore: PrefsStore): AuthRepository {
        return AuthRepositoryImpl(api, prefsStore)
    }

    @Provides
    @Singleton
    fun provideDeibbbo(authRepository: AuthRepository): DribbboRepository {
        return DribbboRepositoryImpl(authRepository)
    }
}