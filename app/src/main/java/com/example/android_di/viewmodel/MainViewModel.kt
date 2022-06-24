package com.example.android_di.viewmodel

import android.util.Log
import android.widget.Adapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_di.model.Post
import com.example.android_di.network.service.PostService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val postService: PostService) : ViewModel() {
    val allPosts = MutableLiveData<ArrayList<Post>>()
    val allPostsrter = MutableLiveData<Post>()

    fun apiPostList() {
        postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>,
            ) {
                allPosts.value = response.body()
                Log.d("MainViewModel",allPosts.value.toString())
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                allPosts.value = ArrayList()
            }
        })
    }

    fun apiDeletePostList(id : Int) {
        postService.deletePost(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                allPostsrter.value = response.body()
                Log.d("ok",response.body().toString())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }
        })
    }

    fun apiCreatePostList(post: Post) {
        postService.createPost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                allPostsrter.value = response.body()
                Log.d("ok",response.body().toString())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }
        })
    }

    fun apiUpdatePostList(id : Int,post: Post) {
        postService.updatePost(id,post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                allPostsrter.value = response.body()
                Log.d("ok",response.body().toString())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }
        })
    }
}