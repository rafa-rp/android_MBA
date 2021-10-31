package com.example.commentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commentsapp.databinding.ActivityCommentsBinding
import com.example.commentsapp.model.CommentMessage
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CommentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentsBinding
    private lateinit var manager: LinearLayoutManager

    private lateinit var db: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: CommentMessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (BuildConfig.DEBUG) {
//            Firebase.database.useEmulator("10.0.2.2", 9000)
//        }
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        if (auth.currentUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }

        // Inicializar o Firebase e o adapter
        db = Firebase.database
        val messagesRef = db.reference.child(MESSAGES_CHILD)

        val options = FirebaseRecyclerOptions.Builder<CommentMessage>()
            .setQuery(messagesRef, CommentMessage::class.java)
            .setLifecycleOwner(this)
            .build()
        adapter = CommentMessageAdapter(options, ANONYMOUS)
        binding.progressBar.visibility = ProgressBar.INVISIBLE
        manager = LinearLayoutManager(this)
        manager.stackFromEnd = true
        binding.messageRecyclerView.layoutManager = manager
        binding.messageRecyclerView.adapter = adapter


        // Possibilita o Scrool das mensagens
        adapter.registerAdapterDataObserver(
            ScrollObserver(binding.messageRecyclerView, adapter, manager)
        )

        // Disabilita o botão de enviar
        binding.messageEditText.addTextChangedListener(ButtonDisable(binding.sendButton))

        // Envia o texto escrito para o Firebase Realtime DataBase
        binding.sendButton.setOnClickListener {
            val commentMessage = CommentMessage()
            commentMessage.name = getUserName()
            commentMessage.text = binding.messageEditText.text.toString()

            db.reference.child(MESSAGES_CHILD).push().setValue(commentMessage)
            binding.messageEditText.setText("")
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in.
        if (auth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }
    }

    private fun getUserName(): String? {
        val user = auth.currentUser
        return if (user != null) {
            user.displayName
        } else ANONYMOUS
    }

    private fun signOut() {
        AuthUI.getInstance().signOut(this)
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    companion object {
        private const val TAG = "MainActivity"
        const val MESSAGES_CHILD = "messages"
        const val ANONYMOUS = "anonymous"
        private const val LOADING_IMAGE_URL = "https://www.google.com/images/spin-32.gif"
    }
}