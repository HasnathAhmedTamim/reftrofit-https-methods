package com.example.jsonplaceholderpractice.domain.model

/**
 * Domain model representing a Post
 * This is the clean data model used throughout the app
 * Independent of API structure
 */

data class Post(
    val id: Int ,
    val userId: Int,
    val title: String,
    val body: String
)

/**
 * If the API response format changes,
 * you only update the mapping in the data layer.
 */