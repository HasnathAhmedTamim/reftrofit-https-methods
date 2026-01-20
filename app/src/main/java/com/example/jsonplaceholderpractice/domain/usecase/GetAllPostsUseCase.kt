package com.example.jsonplaceholderpractice.domain.usecase

import com.example.jsonplaceholderpractice.domain.model.Post
import com.example.jsonplaceholderpractice.domain.repository.PostRepository
import com.example.jsonplaceholderpractice.utils.Resource


/**
 * Use Case for getting all posts
 * Represents single business operation
 * Makes code reusable and testable
 */

class GetAllPostsUseCase(
    private  val repository: PostRepository
){
    suspend operator fun invoke(): Resource<List<Post>> {
        return repository.getAllPosts()
    }
}