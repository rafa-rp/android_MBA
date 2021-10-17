package com.example.commentsapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commentsapp.model.CommentMessage
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CommentMessageAdapter (private val options: FirebaseRecyclerOptions<CommentMessage>,  private val currentUserName: String?):
    FirebaseRecyclerAdapter<CommentMessage, RecyclerView.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        model: CommentMessage
    ) {
        TODO("Not yet implemented")
    }

}
