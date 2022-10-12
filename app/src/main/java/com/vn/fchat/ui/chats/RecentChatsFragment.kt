package com.vn.fchat.ui.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.vn.fchat.R
import com.vn.fchat.databinding.FragmentRecentChatsBinding
import com.vn.fchat.ui.message.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentChatsFragment : Fragment() {

    private lateinit var binding: FragmentRecentChatsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentChatsBinding.inflate(inflater, container, false)
        binding.fragmentRecentChatsTxt.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_recentCallsFragment_to_detailChatFragment)
        }
        return binding.root
    }


}