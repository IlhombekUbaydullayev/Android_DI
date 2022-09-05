package com.example.androiddi.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddi.R
import com.example.androiddi.adapter.RecyclerViewAdapter
import com.example.androiddi.model.Post
import com.example.androiddi.utils.Keys
import com.example.androiddi.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null

    var adapter : RecyclerViewAdapter? = null
    var post : Post? = null
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        Log.d("GetKey", Keys.getPrivateKey().toString())
        Log.d("GetKey", Keys.getPublicKey().toString())
       recyclerView = findViewById(R.id.rv_item)
        recyclerView!!.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = RecyclerViewAdapter({ seletedItem: Post ->listItemClicked(seletedItem)},{ updateItem: Post ->updateItemClicked(updateItem)})
        recyclerView!!.adapter = adapter
        viewModel.allPosts.observe(this, Observer {
            adapter!!.setMovieList(it)
        })
        viewModel.apiPostList()
        val floatButton = findViewById<FloatingActionButton>(R.id.b_floating)
        post = Post(0,0,"Ilhombek2","Tashkent City")
        floatButton.setOnClickListener {
            createPost(post!!)
        }
    }

    private fun updateItemClicked(updateItem: Post) {
        val post = Post(updateItem.id,updateItem.userId,"Ok","Success changed")
        viewModel.apiUpdatePostList(updateItem.id,post)
        Toast.makeText(this,"Success update : ${updateItem.id}",Toast.LENGTH_SHORT).show()
        viewModel.allPosts.observe(this, Observer {
            adapter!!.setMovieList(it)
        })
    }

    private fun createPost(post: Post) {
        viewModel.apiCreatePostList(post)
        Toast.makeText(this,"Success Create : ${post}",Toast.LENGTH_SHORT).show()
        viewModel.allPosts.observe(this, Observer {
            adapter!!.setMovieList(it)
        })
    }

    private fun listItemClicked(seletedItem: Post) {
        viewModel.apiDeletePostList(seletedItem.id)
        Toast.makeText(this,"Success delete : ${seletedItem.id}",Toast.LENGTH_SHORT).show()
        viewModel.allPosts.observe(this, Observer {
            adapter!!.setMovieList(it)
        })
    }
}