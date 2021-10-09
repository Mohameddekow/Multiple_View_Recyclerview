package com.example.multipleviewrecyclerview.repository

import com.example.multipleviewrecyclerview.api.RetrofitInstance
import com.example.multipleviewrecyclerview.model.Post
import com.example.multipleviewrecyclerview.ui.RecyclerViewItem
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<List<RecyclerViewItem.Post>> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getUser(): Response<List<RecyclerViewItem.User>> {
        return RetrofitInstance.api.getUser()
    }

}