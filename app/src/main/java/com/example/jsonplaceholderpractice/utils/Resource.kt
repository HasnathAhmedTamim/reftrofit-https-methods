package com.example.jsonplaceholderpractice.utils

/**
 * Resource wrapper for API responses
 * Helps manage Success, Error, and Loading states cleanly
 */

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
    //    Represents a successful API response
    class Success<T>(data:T):Resource<T>(data)

    //    Represents an error during API call
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)

    //Loading state during APIcall
    class Loading<T>: Resource<T>()
}

/**
 * Wraps api response so we can easily handle success,
 * errors, and
 * loading states
 * without throwing exceptions everywhere.
 */
