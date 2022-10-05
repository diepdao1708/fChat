package com.vn.fchat.data.di

import com.vn.fchat.data.api.AuthService
import com.vn.fchat.data.repository.UserRepository
import com.vn.fchat.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(service: AuthService): UserRepository = UserRepositoryImpl(service)
}