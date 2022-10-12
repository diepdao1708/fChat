package com.vn.fchat.data.di

import com.vn.fchat.data.api.AuthService
import com.vn.fchat.data.api.MessageService
import com.vn.fchat.data.repository.AuthRepository
import com.vn.fchat.data.repository.AuthRepositoryImpl
import com.vn.fchat.data.repository.MessageRepository
import com.vn.fchat.data.repository.MessageRepositoryImpl
import com.vn.fchat.service.ChatSocketService
import com.vn.fchat.service.ChatSocketServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import okhttp3.WebSocket
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(service: AuthService): AuthRepository = AuthRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideMessageRepository(service: MessageService): MessageRepository = MessageRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient{
        return HttpClient(CIO){
            install(Logging)
            install(WebSockets)
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }
    }

    @Provides
    @Singleton
    fun provideMessageService(client: HttpClient): ChatSocketService{
        return ChatSocketServiceImpl(client)
    }
}