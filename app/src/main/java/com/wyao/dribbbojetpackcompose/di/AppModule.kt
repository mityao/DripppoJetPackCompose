package com.wyao.dribbbojetpackcompose.di

import android.content.Context
import com.wyao.dribbbojetpackcompose.common.Constants
import com.wyao.dribbbojetpackcompose.data.AuthorizationInterceptor
import com.wyao.dribbbojetpackcompose.data.remote.AuthApi
import com.wyao.dribbbojetpackcompose.data.remote.DribbboApi
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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.protobuf.ProtoConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(Constants.AUTHORIZE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDribbboApi(authorizationInterceptor: AuthorizationInterceptor): DribbboApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.DRIBBBO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DribbboApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthorizationInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    @Provides
    @Singleton
    fun providePrefsStore(@ApplicationContext context: Context, authorizationInterceptor: AuthorizationInterceptor): PrefsStore {
        return PrefsStoreImpl(context, authorizationInterceptor)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefsStore: PrefsStore): AuthRepository {
        return AuthRepositoryImpl(api, prefsStore)
    }

    @Provides
    @Singleton
    fun provideDribbboRepository(api: DribbboApi, prefsStore: PrefsStore, authRepository: AuthRepository): DribbboRepository {
        return DribbboRepositoryImpl(api, prefsStore, authRepository)
    }
}