package com.example.multipleviewrecyclerview.api

import com.example.multipleviewrecyclerview.ui.RecyclerViewItem
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("photos")
    suspend fun getPost(): Response<List<RecyclerViewItem.Post>>

    @GET("comments")
    suspend fun getUser(): Response<List<RecyclerViewItem.User>>

}