package com.hola360.lwac.data.repository

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.hola360.lwac.data.api.RetrofitHelper
import com.hola360.lwac.data.api.response.submitresponse.SubmitResponse
import com.hola360.lwac.data.api.response.userresponse.SubmitBody
import com.hola360.lwac.data.api.response.userresponse.SubmitItem
import com.hola360.lwac.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubmitThemeRepository(context: Context) {
    private val retrofitHelper: RetrofitHelper = RetrofitHelper.getInstance(context)

    suspend fun submitTheme(
        list: List<SubmitItem>
    ): SubmitResponse? =
        withContext(Dispatchers.Default) {
            try {
                val gson = Gson()
                val json = gson.toJson(SubmitBody(list))
                retrofitHelper.remoteServices.submitTheme(Constants.SUBMIT_TYPE, json)
            } catch (ex: Exception) {
                null
            }
        }
}