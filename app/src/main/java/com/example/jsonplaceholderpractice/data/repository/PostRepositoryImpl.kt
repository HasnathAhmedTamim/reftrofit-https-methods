// kotlin
package com.example.jsonplaceholderpractice.data.repository

import com.example.jsonplaceholderpractice.data.remote.RetrofitInstance
import com.example.jsonplaceholderpractice.data.remote.dto.PostRequestDto
import com.example.jsonplaceholderpractice.data.remote.dto.toPost
import com.example.jsonplaceholderpractice.domain.model.Post
import com.example.jsonplaceholderpractice.domain.repository.PostRepository
import com.example.jsonplaceholderpractice.utils.Resource
import java.lang.Exception

class PostRepositoryImpl : PostRepository {
    private val api = RetrofitInstance.api

    override suspend fun getAllPosts(): Resource<List<Post>> {
        return try {
            // Use the correct API method name (getPosts)
            val response = api.getPosts()

            // Map DTOs to domain models
            // Convert List<PostDto> to List<Post>
            val posts = response.map { postDto ->
                postDto.toPost()
            }
            // Return success with data
            Resource.Success(posts)
        } catch (e: Exception) {
            // If any error occurs, return error with message
            Resource.Error(e.message ?: "An unexpected error occurred")
        }
    }

    /**
     * Fetch single post by ID
     */

    override suspend fun getPostById(postId: Int): Resource<Post> {
        return try {
            val response = api.getPostById(postId)
            Resource.Success(response.toPost())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to fetch post")
        }
    }

    /**
     * Create new post
     * Sends POST request with data in body
     */

    override suspend fun createPost(
        userId: Int,
        title: String,
        body: String
    ): Resource<Post> {
        return try {
            // Create request DTO
            val request = PostRequestDto(
                userId = userId,
                title = title,
                body = body
            )
            // Send POST request
            val response = api.createPost(request)
            Resource.Success(response.toPost())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to create post")
        }
    }

    /**
     * Update existing post
     * Sends PUT request to replace entire post
     */


    override suspend fun updatePost(
        id: Int,
        userId: Int,
        title: String,
        body: String
    ): Resource<Post> {
        return try {
            val request = PostRequestDto(userId, title, body)
            val response = api.updatePost(id, request)
            Resource.Success(response.toPost())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to update post")
        }
    }

    /**
     * Delete post
     * Returns true if successful
     */

    override suspend fun deletePost(id: Int): Resource<Boolean> {
        return try {
            val response = api.deletePost(id)
            // Check if HTTP response was successful (200-299 status code)
            Resource.Success(response.isSuccessful)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to delete post")
        }
    }
}
