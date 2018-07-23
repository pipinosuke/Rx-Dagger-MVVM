package com.example.sugino.dagger_mvvm.network

import com.example.sugino.dagger_mvvm.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
fun getPosts(): Observable<List<Post>>
}