package com.example.multipleviewrecyclerview.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multipleviewrecyclerview.repository.Repository
import com.example.multipleviewrecyclerview.ui.RecyclerViewItem
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    var myResponse: MutableLiveData<Response<List<RecyclerViewItem>>> = MutableLiveData()

    fun getAllItems() {

        viewModelScope.launch {

            //@TODO fetching the  deferred  and change to a response from the api
//            val postResponseDeferred = async { repository.getPost() }
//            val userResponseDeferred = async { repository.getUser() }
//
//            val post2 = postResponseDeferred.await()
//            val user2 = userResponseDeferred.await()

            //@TODO u can instead use this 2 lines of code directly fetch response
            val post = repository.getPost()
            val user = repository.getUser()

            val recyclerViewListItems = mutableListOf<RecyclerViewItem>()

            recyclerViewListItems.add(RecyclerViewItem.Title(1, "Users"))
            user.body()?.let { recyclerViewListItems.addAll(it) }


            recyclerViewListItems.add(RecyclerViewItem.Title(2, "Recommended Post"))
            post.body()?.let { recyclerViewListItems.addAll(it) }


            myResponse.postValue(Response.success(recyclerViewListItems))


        }
    }

}
