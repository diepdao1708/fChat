package com.vn.fchat.data.model

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
