package com.vn.fchat.service

import com.vn.fchat.data.model.Message
import com.vn.fchat.ui.utils.Resource
import kotlinx.coroutines.flow.Flow


interface ChatSocketService {

    suspend fun initSession(username: String): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()
}