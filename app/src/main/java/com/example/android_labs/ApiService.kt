package com.example.android_labs

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    fun getPosts(): Call<MutableList<PostModel>>
}