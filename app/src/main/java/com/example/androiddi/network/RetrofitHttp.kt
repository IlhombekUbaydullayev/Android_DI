package com.example.androiddi.network

import com.example.androiddi.network.service.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHttp {
    private const val IS_TESTER = false
    private const val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
    private const val SERVER_PRODUCTION = "https://62c58071a361f7251286730b.mockapi.io/"

    @Provides
    fun server():String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    @Provides
    @Singleton
    fun retrofitClient() : Retrofit {
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun postService() : PostService {
        return retrofitClient().create(PostService::class.java)
    }

}