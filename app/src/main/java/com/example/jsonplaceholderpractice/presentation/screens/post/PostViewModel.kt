package com.example.jsonplaceholderpractice.presentation.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonplaceholderpractice.data.repository.PostRepositoryImpl
import com.example.jsonplaceholderpractice.domain.model.Post
import com.example.jsonplaceholderpractice.domain.usecase.CreatePostUseCase
import com.example.jsonplaceholderpractice.domain.usecase.GetAllPostsUseCase
import com.example.jsonplaceholderpractice.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Data class holding all UI state
 * Single source of truth for the screen
 */
data class PostState(
    val posts: List<Post> = emptyList(),      // List of all posts
    val selectedPost: Post? = null,            // Currently selected/created post
    val isLoading: Boolean = false,            // Loading indicator
    val message: String = "",                  // Success message
    val error: String = ""                     // Error message
)

/**
 * ViewModel for Post screen
 * Manages UI state and business logic
 * Survives configuration changes (screen rotation)
 */

class PostViewModel : ViewModel(){

    // Initialize repository and use cases
    private val repository = PostRepositoryImpl()
    private val getAllPostsUseCase = GetAllPostsUseCase(repository)
    private val createPostUseCase = CreatePostUseCase(repository)


    // Private mutable state - only ViewModel can modify
    private val _state = MutableStateFlow(PostState())

    // Public read-only state - UI can observe
    val state: StateFlow<PostState> = _state.asStateFlow()

    /**
     * Fetch all posts from API
     * Called when user clicks "GET All Posts" button
     */
    fun fetchAllPosts() {
        // Launch coroutine in viewModelScope
        // Automatically cancelled when ViewModel is destroyed
        viewModelScope.launch {

            // Set loading state
            _state.value = _state.value.copy(isLoading = true)

            // Call use case
            when (val result = getAllPostsUseCase()) {

                is Resource.Success -> {
                    // Update state with posts data
                    _state.value = _state.value.copy(
                        posts = result.data ?: emptyList(),
                        isLoading = false,
                        message = "Loaded ${result.data?.size ?: 0} posts",
                        error = ""
                    )
                }

                is Resource.Error -> {
                    // Update state with error message
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Unknown error",
                        message = ""
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }
    }

    /**
     * Create new post
     * Called when user clicks "POST Create New Post"
     */
    fun createPost(userId: Int, title: String, body: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            // Call use case (includes validation)
            when (val result = createPostUseCase(userId, title, body)) {

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        selectedPost = result.data,
                        isLoading = false,
                        message = "Created post successfully! ID: ${result.data?.id}",
                        error = ""
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Failed to create post",
                        message = ""
                    )
                }

                is Resource.Loading -> {}
            }
        }
    }


    /**
     * Clear messages
     */
    fun clearMessages() {
        _state.value = _state.value.copy(
            message = "",
            error = ""
        )
    }

}

/**
 * How StateFlow works:
 *
 * ViewModel has MutableStateFlow (can update)
 *
 * UI observes StateFlow (read-only)
 *
 * When state changes, UI automatically recomposes
 *
 * UI always shows current state
 * */