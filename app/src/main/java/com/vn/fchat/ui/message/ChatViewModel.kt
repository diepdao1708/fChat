package com.vn.fchat.ui.message

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vn.fchat.data.api.MessageService
import com.vn.fchat.data.model.ChatState
import com.vn.fchat.data.model.MessageDto
import com.vn.fchat.data.repository.MessageRepository
import com.vn.fchat.data.repository.MessageRepositoryImpl
import com.vn.fchat.service.ChatSocketService
import com.vn.fchat.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository,
    private val chatSocketService: ChatSocketService
) : ViewModel() {
    private val messageText : MutableLiveData<String>? = MutableLiveData()
    fun getMessageText(): MutableLiveData<String> {
        return messageText?: MutableLiveData()
    }

    private val state : MutableLiveData<ChatState>? = MutableLiveData()
    fun getState(): MutableLiveData<ChatState>{
        return state ?: MutableLiveData()
    }
    private val toastEvent: MutableLiveData<String>? = MutableLiveData()
    fun getToastEvent():  MutableLiveData<String>{
        return toastEvent ?: MutableLiveData()
    }

    fun onMessageChange(message: String){
        messageText?.value = message
    }

    fun startChat(username: String){
        getAllMessages()
       username.let { it->
            viewModelScope.launch {
                val result = chatSocketService.initSession(it)
                when(result){
                    is Resource.Success -> {
                        chatSocketService.observeMessages().onEach { message ->
                            val newList = state?.value?.messages?.toMutableList()?.apply {
                                add(0,message)
                            }
                            state?.value = newList?.let {
                                state?.value?.copy(
                                    messages = it
                                )
                            }

                        }.launchIn(viewModelScope)
                    }
                    is Resource.Error -> {
                        toastEvent?.postValue(result.message ?: "Unknown error")
                    }
                    else -> {}
                }
            }
        }
    }

    fun disconnect(){
        viewModelScope.launch {
            chatSocketService.closeSession()
        }
    }

    fun sendMessage(){
        viewModelScope.launch {
            if(messageText?.value?.isNotBlank() == true){
                chatSocketService.sendMessage(messageText.value!!)
            }

        }
    }

    fun getAllMessages(){
        viewModelScope.launch {
            val result = messageRepository.getAllMessage()
            for( item in result){
                state?.value = ChatState(result,false)
            }
        }
    }
}