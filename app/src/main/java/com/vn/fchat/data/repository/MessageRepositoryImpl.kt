package com.vn.fchat.data.repository

import com.vn.fchat.data.api.MessageService
import com.vn.fchat.data.model.Message
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(private val service: MessageService) : MessageRepository{
    override suspend fun getAllMessage(): List<Message> {
        return service.getAllMessage().map { it.toMessage() }
    }
}