package com.example.commentsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commentsapp.CommentsActivity.Companion.ANONYMOUS
import com.example.commentsapp.databinding.MessageBinding
import com.example.commentsapp.model.CommentMessage
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CommentMessageAdapter (private val options: FirebaseRecyclerOptions<CommentMessage>,  private val currentUserName: String?):
    FirebaseRecyclerAdapter<CommentMessage, RecyclerView.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.message, parent, false)
        val binding = MessageBinding.bind(view)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, model: CommentMessage) {
            (holder as MessageViewHolder).bind(model)
    }

    inner class MessageViewHolder(private val binding: MessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentMessage) {
            binding.messageTextView.text = item.text

            binding.messengerTextView.text = ANONYMOUS
            binding.messengerImageView.setImageResource(R.drawable.ic_account_circle_black_36dp)
        }
    }

}
