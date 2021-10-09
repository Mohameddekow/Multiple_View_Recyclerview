package com.example.multipleviewrecyclerview.ui

sealed class RecyclerViewItem() {

    class Title(
        val id: Int,
        val title: String
    ) : RecyclerViewItem()

    class Post(
        val albumId: Int,
        val id: Int,
        val title: String,
        val url: String,
        val thumbnailUrl: String
    ) : RecyclerViewItem()

    class User(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val  body: String
    ): RecyclerViewItem()

 
}
