package com.vn.fchat.data.model

import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*

@Serializable
data class MessageDto (
    val text: String,
    val timestamp: Long,
    val username: String,
    val id: String
    ){
    fun toMessage(): Message{
        val date = Date(timestamp)
        val formattedDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date)

        return Message(
            text = text,
            formattedTime = formattedDate.toString(),
            username = username
        )
    }
}