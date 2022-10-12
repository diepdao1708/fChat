package com.vn.fchat.data.repository

import com.vn.fchat.data.model.Message

interface MessageRepository {
    suspend fun getAllMessage(): List<Message>
}