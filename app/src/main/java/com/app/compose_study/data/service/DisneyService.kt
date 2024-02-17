package com.app.compose_study.data.service

import com.app.compose_study.data.model.Post
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface DisneyService {
    @GET("DisneyPosters2.json")
    suspend fun fetchDisneyPosterList(): ApiResponse<List<Post>>
}