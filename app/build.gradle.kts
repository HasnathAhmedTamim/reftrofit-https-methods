plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.jsonplaceholderpractice"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.jsonplaceholderpractice"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    // ViewModel for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines for async operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // ============ RECOMMENDED ADDITIONS ============

    // 1. OkHttp Logging Interceptor - See API requests/responses in Logcat
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // 2. Coil - Image loading library (useful when working with image URLs)
    implementation("io.coil-kt:coil-compose:2.5.0")

    // 3. Gson - Already have converter, but this helps with custom JSON parsing
    implementation("com.google.code.gson:gson:2.10.1")

    // 4. Compose Navigation - For multi-screen apps (future enhancement)
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // 5. Accompanist - Utilities for Compose (pull-to-refresh, system UI controller)
    implementation("com.google.accompanist:accompanist-swiperefresh:0.32.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    // 6. Room Database - For offline caching (advanced feature)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    // kapt("androidx.room:room-compiler:2.6.1") // Requires kapt plugin

    // 7. DataStore - Modern replacement for SharedPreferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // 8. Timber - Better logging
    implementation("com.jakewharton.timber:timber:5.0.1")
}