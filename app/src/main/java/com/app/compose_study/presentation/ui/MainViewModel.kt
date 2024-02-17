package com.app.compose_study.presentation.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose_study.data.model.Post
import com.app.compose_study.data.repository.DefaultDisneyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val disneyRepository: DefaultDisneyRepository
): ViewModel() {
    private val _posts : MutableStateFlow<List<Post>> = MutableStateFlow(listOf())
    val post: StateFlow<List<Post>> get() = _posts
    fun getPosts() {
        viewModelScope.launch {
            disneyRepository.getDisneyPosts(
                onStart = {},
                onCompletion = {},
                onError =  {}
            ).collect {
                _posts.value = it
            }
        }
    }
}