package com.example.jsonplaceholderpractice.data.remote.dto

import com.example.jsonplaceholderpractice.domain.model.Post

/**
 * DTO = Data Transfer Object
 * Represents the structure of data from API
 * May have different fields than domain model
 */

data class PostDto(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

/**
 * Extension function to convert DTO to Domain Model
 * Keeps domain layer clean and independent of API structure
 */

fun PostDto.toPost(): Post{
    return Post(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}


/**
 * Request DTO for POST and PUT operations
 * Doesn't include 'id' because API generates it
 */
data class PostRequestDto(
    val userId: Int,
    val title: String,
    val body: String
)