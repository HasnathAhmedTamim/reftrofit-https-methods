package com.example.jsonplaceholderpractice.domain.usecase

import com.example.jsonplaceholderpractice.domain.model.Post
import com.example.jsonplaceholderpractice.domain.repository.PostRepository
import com.example.jsonplaceholderpractice.utils.Resource


/**
 * Use Case for creating a new post
 * Can add validation logic here before calling repository
 */


class CreatePostUseCase(
    private val repository: PostRepository
){
    suspend operator fun invoke(
        userId: Int,
        title: String,
        body: String
    ): Resource<Post> {
        // Validation: check if title is not empty
        if (title.isBlank()) {
            return Resource.Error("Title cannot be empty")
        }
        if (body.isBlank()){
            return Resource.Error("Body cannot be empty")
        }
        // if validation passes, create the post
        return repository.createPost(userId, title, body)
    }

}
