package com.example.sugino.dagger_mvvm.network

import com.example.sugino.dagger_mvvm.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    @GET("items?page=1&per_page=1")
//fun getPosts(@Query("query") query: String? = null,
//             @Query("id") id: Int = 1): Observable<List<Post>>
    fun getPosts(@Query("query") query: String? = null,
                 @Query("page") page: Int = 1,
                 @Query("per_page") per_page: Int = 20 ): Observable<List<Post>>
}