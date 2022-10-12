package com.vn.fchat.data.api

import com.vn.fchat.data.model.Message
import com.vn.fchat.data.model.MessageDto
import com.vn.fchat.ui.utils.Constants.BASE_URL
import retrofit2.http.GET

interface MessageService {

    @GET("$BASE_URL/messages")
    suspend fun getAllMessage(): List<MessageDto>



}