package com.example.commentsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commentsapp.databinding.ActivityCommentsBinding
import com.example.commentsapp.model.CommentMessage
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CommentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentsBinding
    private lateinit var manager: LinearLayoutManager

    private lateinit var db: FirebaseDatabase
    private lateinit var adapter: CommentMessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (BuildConfig.DEBUG) {
//            Firebase.database.useEmulator("10.0.2.2", 9000)
//        }

        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.database
        val messagesRef = db.reference.child(MESSAGES_CHILD)

        val options = FirebaseRecyclerOptions.Builder<CommentMessage>()
            .setQuery(messagesRef, CommentMessage::class.java)
            .build()
        adapter = CommentMessageAdapter(options, ANONYMOUS)
        binding.progressBar.visibility = ProgressBar.INVISIBLE
        manager = LinearLayoutManager(this)
        manager.stackFromEnd = true
        binding.messageRecyclerView.layoutManager = manager
        binding.messageRecyclerView.adapter = adapter


        // Disable the send button when there's no text in the input field
        // See MyButtonObserver for details
        binding.messageEditText.addTextChangedListener(ButtonDisable(binding.sendButton))

        // When the send button is clicked, send a text message
        binding.sendButton.setOnClickListener {
            val commentMessage = CommentMessage(
                binding.messageEditText.text.toString(),
                ANONYMOUS
            )
            db.reference.child(MESSAGES_CHILD).push().setValue(commentMessage)
            binding.messageEditText.setText("")
        }
    }


    companion object {
        private const val TAG = "MainActivity"
        const val MESSAGES_CHILD = "messages"
        const val ANONYMOUS = "anonymous"
        private const val LOADING_IMAGE_URL = "https://www.google.com/images/spin-32.gif"
    }
}