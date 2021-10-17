package com.example.commentsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun acessarComentarios(view: View) {
        val intent = Intent(this, CommentsActivity::class.java)
        startActivity(intent)
    }
}