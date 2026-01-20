package com.example.jsonplaceholderpractice.data.remote

import com.example.jsonplaceholderpractice.data.remote.api.JsonPlaceholderAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for Retrofit instance
 * Only one instance is created and reused throughout the app
 */

object RetrofitInstance{
    // Base URL - common part of all API endpoints
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    /**
     * Lazy initialization of Retrofit
     * Created only when first accessed
     */

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * API interface instance
     * Retrofit implements this interface automatically
     */

    val api : JsonPlaceholderAPI by lazy {
        retrofit.create(JsonPlaceholderAPI::class.java)
    }

}

/**
 * How Retrofit works:
 *
 * You define interface with endpoints
 *
 * Retrofit creates implementation automatically
 *
 * Gson converts JSON ↔ Kotlin objects
 *
 * You just call functions like api.getAllPosts()
 *
 * */