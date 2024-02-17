package com.app.compose_study.data.repository

import com.app.compose_study.data.service.DisneyService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiResponse.Companion.exception
import com.skydoves.sandwich.ApiResponse.Companion.operate
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DefaultDisneyRepository @Inject constructor(private val disneyService: DisneyService) {
    suspend fun getDisneyPosts(
        onStart: ()-> Unit,
        onCompletion: () -> Unit,
        onError: () -> Unit
    ) = flow {
        disneyService.fetchDisneyPosterList().suspendOnSuccess {
            emit(data)
        }.onError {

        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}