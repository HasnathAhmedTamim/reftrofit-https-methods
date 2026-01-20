package com.example.jsonplaceholderpractice.data.remote.api

import com.example.jsonplaceholderpractice.data.remote.dto.PostDto
import com.example.jsonplaceholderpractice.data.remote.dto.PostRequestDto
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit API interface
 * Defines all API endpoints
 */

interface JsonPlaceholderAPI {
    /**
     * GET request to fetch all posts
     * URL: https://jsonplaceholder.typicode.com/posts
     */

    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    /**
     * GET request to fetch single post by ID
     * URL: https://jsonplaceholder.typicode.com/posts/{id}
     * Example: posts/1, posts/2, etc.
     */

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): PostDto

    /**
     * POST request to create new post
     * Sends data in request body as JSON
     */

    @POST("posts")
    suspend fun createPost(@Body post: PostRequestDto): PostDto

    /**
     * PUT request to update entire post
     * Replaces all fields of existing post
     */

    @PUT("posts/{id}")
    suspend fun updatePost(
        @Path("id") id: Int,
        @Body post: PostRequestDto
    ): PostDto

    /**
     * DELETE request to remove post
     * Returns Response wrapper to check success
     */
    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>
}


/**
 * Retrofit Annotations Explained:
 *
 * @GET("posts"): Makes GET request to base_url + "posts"
 *
 * @Path("id"): Replaces {id} in URL with provided value
 *
 * @Body: Sends object as JSON in request body
 *
 * suspend: Makes function work with coroutines
 * coroutines: mean lightweight threads for async code for
 * non-blocking operations for example network calls
 * where we don't want to block the main thread
 * and keep the app responsive
 *
 * */