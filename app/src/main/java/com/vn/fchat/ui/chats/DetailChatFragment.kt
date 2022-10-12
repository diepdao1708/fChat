package com.vn.fchat.ui.chats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.vn.fchat.R
import com.vn.fchat.data.model.Message
import com.vn.fchat.databinding.FragmentDetailChatBinding
import com.vn.fchat.databinding.FragmentRecentChatsBinding
import com.vn.fchat.ui.message.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailChatFragment : Fragment() {
    private val chatViewModel by viewModels<ChatViewModel>()
    private lateinit var binding: FragmentDetailChatBinding
    private val listData : MutableList<Message>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailChatBinding.inflate(inflater, container, false)
        setUpViewModel()
        return binding.root
    }

    private fun setUpViewModel(){
        chatViewModel.startChat("Long")
        chatViewModel.getState().observe(viewLifecycleOwner, Observer { it ->
            it?.messages?.toMutableList()?.let { it1 ->
                listData?.addAll(it1)
            }
        })
    }

    private fun setUpRecyclerView(){
        binding.detailChatRv.adapter = DetailChatAdapter(listData?: mutableListOf())
    }
}