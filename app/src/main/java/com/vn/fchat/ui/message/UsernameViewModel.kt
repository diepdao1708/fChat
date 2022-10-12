package com.vn.fchat.ui.message

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsernameViewModel @Inject constructor(): ViewModel() {
    private val _usernameText = MutableLiveData<String>()
    val usernameText = _usernameText
    private val _onJoinChat = MutableLiveData<String>()
    val onJoinChat = _onJoinChat

    fun onUsernameChange(username: String){
        _usernameText.value = username
    }

    fun onJoinClick(){
        viewModelScope.launch {
            if(usernameText.value?.isNotBlank() == true){
                _onJoinChat.postValue(usernameText.value)
            }
        }
    }
}