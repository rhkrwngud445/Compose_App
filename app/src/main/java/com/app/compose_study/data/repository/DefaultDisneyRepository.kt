package com.app.compose_study.data.repository

import com.app.compose_study.data.service.DisneyService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class DefaultDisneyRepository @Inject constructor(private val disneyService: DisneyService) {
    suspend fun getDisneyPosts(): ApiResponse<List<Int>> {
        return disneyService.fetchDisneyPosterList()
    }
}