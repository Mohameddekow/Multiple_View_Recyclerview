package com.example.multipleviewrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multipleviewrecyclerview.ui.MyAdapter
import com.example.multipleviewrecyclerview.databinding.ActivityMainBinding
import com.example.multipleviewrecyclerview.repository.Repository
import com.example.multipleviewrecyclerview.ui.RecyclerViewItem
import com.example.multipleviewrecyclerview.util.*
import com.example.multipleviewrecyclerview.viewmodel.MainViewModel
import com.example.multipleviewrecyclerview.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: Repository
    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myAdapter = MyAdapter()

//        setSnackBar(findViewById(R.id.mainAct), this, "Snack bar is launched")

        myAdapter.itemClickListener = { view, item, position ->
            val message = when(item){
                is RecyclerViewItem.Post -> "Post ${item.id} ${item.title}  is clicked"
                is RecyclerViewItem.Title ->  "Title ${item.id} ${item.title}  is clicked"
                is RecyclerViewItem.User ->  "User ${item.id} ${item.email}  is clicked"
            }

            setSnackBar(findViewById(R.id.mainAct), this, message)
        }

        //enabling their visibility
        enableView(binding.progressBar)
        enableView(binding.textView)

        //recyclerView
        setupRecyclerView()

        repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllItems()

        viewModel.myResponse.observe(this, Observer { response ->

            //disabling their visibility
            disableView(binding.progressBar)
            disableView(binding.textView)

            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
            } else {
                setToast(this, response.code().toString())
            }
        })

    }

    private fun setupRecyclerView() {
          binding.apply {

              myRecyclerView.adapter = myAdapter
              myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
          }
    }
}