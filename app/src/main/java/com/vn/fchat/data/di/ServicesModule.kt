package com.vn.fchat.data.di

import android.os.Build.VERSION_CODES.M
import com.vn.fchat.data.api.AuthService
import com.vn.fchat.data.api.MessageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Singleton
    @Provides
    fun provideRemoteServices(
        retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideRemoteMessageServices(
        retrofit: Retrofit
    ): MessageService = retrofit.create(MessageService::class.java)
}