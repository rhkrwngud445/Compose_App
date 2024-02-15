package com.app.compose_study.presentation.ui

import androidx.lifecycle.ViewModel
import com.app.compose_study.data.model.Post
import com.app.compose_study.data.repository.DefaultDisneyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val disneyRepository: DefaultDisneyRepository
): ViewModel() {
    fun getPosts(): Flow<List<Post>> = flow {
        disneyRepository.getDisneyPosts()
    }
}