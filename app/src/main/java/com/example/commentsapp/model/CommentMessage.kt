package com.example.commentsapp.model

class CommentMessage {
    var text: String? = null
    var name: String? = null

    constructor(text: String?, name: String?){
        this.text = text
        this.name = name
    }
}