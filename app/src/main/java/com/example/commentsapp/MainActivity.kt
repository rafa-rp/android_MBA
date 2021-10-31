package com.example.commentsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun acessarComentarios(view: View) {
        val intent = Intent(this, CommentsActivity::class.java)
        startActivity(intent)
    }

    fun loginUser(view: View){
        val intent= Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    fun logoutUser(view: View){
        AuthUI.getInstance().signOut(this)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}