package com.example.jsonplaceholderpractice.presentation.screens.post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jsonplaceholderpractice.presentation.components.PostCard

/**
 * Main screen composable
 * Displays UI and handles user interactions
 */

@Composable
fun PostScreen(
    viewModel: PostViewModel = viewModel()
) {
    // Collect state from ViewModel
    // collectAsState() converts StateFlow to Compose State
    val state by viewModel.state.collectAsState()

    // Local state for text input fields
    var titleInput by remember { mutableStateOf("") }
    var bodyInput by remember { mutableStateOf("") }


    // Main column layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // === HEADER ===
        Text(
            text = "JSONPlaceholder API Practice",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Learn Retrofit with all HTTP methods",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // === SUCCESS MESSAGE ===
        if (state.message.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "✓ ${state.message}",
                    color = Color.White,
                    modifier = Modifier.padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // === ERROR MESSAGE ===
        if (state.error.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF44336)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "✗ ${state.error}",
                    color = Color.White,
                    modifier = Modifier.padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // === LOADING INDICATOR ===
        if (state.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }


        // === GET METHOD SECTION ===
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "HTTP GET Method",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Fetch data from server",
                    style = MaterialTheme.typography.labelSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { viewModel.fetchAllPosts() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isLoading
                ) {
                    Text(text = "GET All Posts")
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // === POST METHOD SECTION ===

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF9C4)
            )
        ) {

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "HTTP POST Method",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Create new resource on server",
                    style = MaterialTheme.typography.labelSmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Title input
                OutlinedTextField(
                    value = titleInput,
                    onValueChange = { titleInput = it },
                    label = { Text("Post Title") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Body input
                OutlinedTextField(
                    value = bodyInput,
                    onValueChange = { bodyInput = it },
                    label = { Text("Post Body") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        // Validate and create post
                        if (titleInput.isNotBlank() && bodyInput.isNotBlank()) {
                            viewModel.createPost(1, titleInput, bodyInput)
                            // Clear inputs
                            titleInput = ""
                            bodyInput = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isLoading &&
                            titleInput.isNotBlank() &&
                            bodyInput.isNotBlank()
                ) {
                    Text("POST Create New Post")
                }

            }

        }

        Spacer(modifier = Modifier.height(16.dp))


        // === DISPLAY ALL POSTS ===
        if (state.posts.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "All Posts",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${state.posts.size} posts",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Scrollable list of posts
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.posts) { post ->
                    PostCard(post = post)
                }
            }

        }


    }


}



/**
 *Compose concepts used:
 *
 * remember: Saves state across recompositions
 *
 * collectAsState(): Observes StateFlow and triggers recomposition
 *
 * LazyColumn: Efficient scrolling list (like RecyclerView)
 *
 * Card: Material Design card component
 * */