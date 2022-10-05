package com.vn.fchat.data.di

import com.vn.fchat.data.api.AuthService
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
}