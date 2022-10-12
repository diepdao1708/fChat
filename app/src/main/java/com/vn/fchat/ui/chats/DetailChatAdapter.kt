package com.vn.fchat.ui.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vn.fchat.data.model.Message
import com.vn.fchat.databinding.ItemCurrentChatBinding
import com.vn.fchat.databinding.ItemOppositeChatBinding
import com.vn.fchat.ui.utils.Constants

class DetailChatAdapter(private val listData: MutableList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.USER_CHAT) {
            val view =
                ItemCurrentChatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            UserChatViewHolder(view)
        } else {
            val view =
                ItemOppositeChatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            OppositeChatViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listData[position].username == "Long") Constants.USER_CHAT else Constants.OPPOSITE_CHAT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == Constants.USER_CHAT){
            (holder as UserChatViewHolder).bind(listData[position])
        }
        else (holder as OppositeChatViewHolder).bind(listData[position])
    }

    inner class UserChatViewHolder(private val itemCurrentChatBinding: ItemCurrentChatBinding): RecyclerView.ViewHolder(itemCurrentChatBinding.root){
        fun bind(message: Message){
            itemCurrentChatBinding.apply {
                timeTv.text = message.formattedTime
                contentTv.text = message.text
            }
        }

    }

    inner class OppositeChatViewHolder(private val itemOppositeChatBinding: ItemOppositeChatBinding): RecyclerView.ViewHolder(itemOppositeChatBinding.root){
        fun bind(message: Message){
            itemOppositeChatBinding.apply {
                timeTv.text = message.formattedTime
                contentTv.text = message.text
                nameTv.text = message.username
            }
        }
    }

    override fun getItemCount() = listData.size

}
