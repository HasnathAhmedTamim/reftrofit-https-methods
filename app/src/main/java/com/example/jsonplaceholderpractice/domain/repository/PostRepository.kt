package com.example.jsonplaceholderpractice.domain.repository


import com.example.jsonplaceholderpractice.domain.model.Post
import com.example.jsonplaceholderpractice.utils.Resource

/**
 * Repository interface (contract)
 * Defines WHAT operations are available
 * Actual implementation is in data layer
 */

interface PostRepository{
    // Get all posts from API
    suspend fun getAllPosts(): Resource<List<Post>>

    // Get single post by ID
    suspend fun getPostById(postId: Int): Resource<Post>

    // Create new post
    suspend fun createPost(
        userId: Int,
        title: String,
        body: String
    ): Resource<Post>

    // Update existing post
    suspend fun updatePost(
        id: Int,
        userId: Int,
        title: String,
        body: String
    ): Resource<Post>

    // Delete post byID
    suspend fun deletePost(id: Int): Resource<Boolean>
}


/**
 * What is suspend?: Marks function as "suspendable" - can be paused and resumed.
 * Used with Kotlin coroutines for async operations without blocking the UI.
 *
 * */