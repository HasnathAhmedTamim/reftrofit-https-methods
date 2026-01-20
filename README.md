# 📱 JSONPlaceholder Practice - Android App

A modern Android application built with **Jetpack Compose** that demonstrates best practices for consuming RESTful APIs using **Clean Architecture** and **MVVM pattern**. This project uses the [JSONPlaceholder API](https://jsonplaceholder.typicode.com/) to perform CRUD operations on posts.

---

## 🎯 Project Overview

This app is a learning project showcasing how to:
- Implement **Clean Architecture** with proper separation of concerns
- Use **Retrofit** for network requests
- Apply **MVVM** (Model-View-ViewModel) pattern
- Build reactive UIs with **Jetpack Compose**
- Handle async operations using **Kotlin Coroutines**
- Manage state effectively with **StateFlow**

---

## ✨ Features Implemented

### 📡 API Operations (CRUD)
- ✅ **GET** - Fetch all posts from API
- ✅ **GET** - Fetch single post by ID
- ✅ **POST** - Create new post
- ✅ **PUT** - Update existing post
- ✅ **DELETE** - Delete post by ID

### 🎨 UI Features
- ✅ Post list display with custom `PostCard` components
- ✅ Loading indicators during API calls
- ✅ Success/Error message cards
- ✅ Material 3 Design System
- ✅ Responsive UI with Jetpack Compose

### 🏗️ Architecture Features
- ✅ Clean Architecture (Data → Domain → Presentation)
- ✅ MVVM Pattern with ViewModel
- ✅ Use Cases for business logic
- ✅ Repository Pattern for data abstraction
- ✅ Resource wrapper for type-safe error handling

---

## 🛠️ Tech Stack

### Core Technologies
| Technology | Version | Purpose |
|------------|---------|---------|
| **Kotlin** | Latest | Primary programming language |
| **Jetpack Compose** | Latest | Modern declarative UI toolkit |
| **Material 3** | Latest | Design system and UI components |

### Networking & Async
| Library | Version | Purpose |
|---------|---------|---------|
| **Retrofit** | 2.9.0 | REST API client |
| **Gson Converter** | 2.9.0 | JSON serialization/deserialization |
| **OkHttp Logging** | 4.12.0 | Network request/response logging |
| **Coroutines** | 1.7.3 | Async programming |

### Jetpack Components
| Component | Version | Purpose |
|-----------|---------|---------|
| **ViewModel** | 2.7.0 | Lifecycle-aware state management |
| **Lifecycle Runtime** | Latest | Lifecycle handling |
| **Navigation Compose** | 2.7.7 | Screen navigation (future) |

### Additional Libraries
| Library | Version | Purpose |
|---------|---------|---------|
| **Coil** | 2.5.0 | Image loading (future use) |
| **Accompanist** | 0.32.0 | Compose utilities (swipe-refresh, etc.) |
| **Room** | 2.6.1 | Local database (future offline support) |
| **DataStore** | 1.0.0 | Modern key-value storage |
| **Timber** | 5.0.1 | Enhanced logging |

---

## 📁 Project Structure

```
com.example.jsonplaceholderpractice/
│
├── 📂 data/                          # Data Layer (External data sources)
│   ├── 📂 remote/                    # Network-related code
│   │   ├── 📂 api/
│   │   │   └── JsonPlaceholderAPI.kt # Retrofit API interface
│   │   ├── 📂 dto/                   # Data Transfer Objects
│   │   │   ├── PostDto.kt            # API response model
│   │   │   └── PostRequestDto.kt     # API request model
│   │   └── RetrofitInstance.kt       # Retrofit singleton setup
│   └── 📂 repository/
│       └── PostRepositoryImpl.kt     # Repository implementation
│
├── 📂 domain/                        # Domain Layer (Business logic)
│   ├── 📂 model/
│   │   └── Post.kt                   # Domain model (clean data)
│   ├── 📂 repository/
│   │   └── PostRepository.kt         # Repository interface (contract)
│   └── 📂 usecase/                   # Business logic use cases
│       ├── GetAllPostsUseCase.kt     # Fetch posts use case
│       └── CreatePostUseCase.kt      # Create post use case
│
├── 📂 presentation/                  # Presentation Layer (UI)
│   ├── 📂 components/
│   │   └── PostCard.kt               # Reusable post card UI
│   └── 📂 screens/
│       └── 📂 post/
│           ├── PostScreen.kt         # Main screen composable
│           ├── PostViewModel.kt      # State & logic for screen
│           └── PostState.kt          # UI state data class
│
├── 📂 ui/theme/                      # Theming & styling
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
├── 📂 utils/                         # Utilities
│   └── Resource.kt                   # Sealed class for API states
│
└── MainActivity.kt                   # App entry point
```

---

## 🏛️ Clean Architecture Layers

### 1️⃣ **Data Layer** (External World)
- **What**: Handles data from external sources (APIs, databases)
- **Contains**:
  - `JsonPlaceholderAPI`: Retrofit interface with HTTP endpoints
  - `PostDto`: Raw data from API
  - `PostRepositoryImpl`: Actual implementation of data fetching
- **Dependency**: Depends on **Domain Layer** (implements domain interfaces)

### 2️⃣ **Domain Layer** (Business Logic)
- **What**: Pure business logic, independent of Android/frameworks
- **Contains**:
  - `Post`: Clean domain model
  - `PostRepository`: Interface defining data operations
  - `GetAllPostsUseCase`, `CreatePostUseCase`: Business rules
- **Dependency**: No dependencies (most inner layer)

### 3️⃣ **Presentation Layer** (UI)
- **What**: Everything the user sees and interacts with
- **Contains**:
  - `PostScreen`: Jetpack Compose UI
  - `PostViewModel`: Manages UI state and user actions
  - `PostState`: Data class holding UI state
  - `PostCard`: Reusable UI component
- **Dependency**: Depends on **Domain Layer** (calls use cases)

---

## 🔄 How It Works (Data Flow)

```
┌─────────────────────────────────────────────────────────────┐
│                    USER INTERACTION                          │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│  📱 PRESENTATION LAYER                                       │
│  ┌──────────────┐         ┌─────────────────┐              │
│  │  PostScreen  │────────▶│  PostViewModel  │              │
│  │ (Composable) │         │  (State Logic)  │              │
│  └──────────────┘         └────────┬────────┘              │
│                                     │                        │
│                            Calls Use Case                    │
└─────────────────────────────────────┼───────────────────────┘
                                      │
                                      ▼
┌─────────────────────────────────────────────────────────────┐
│  🧠 DOMAIN LAYER                                             │
│  ┌──────────────────────┐                                   │
│  │  GetAllPostsUseCase  │  ← Business Logic & Validation    │
│  └──────────┬───────────┘                                   │
│             │                                                 │
│    Calls Repository Interface                                │
└─────────────┼───────────────────────────────────────────────┘
              │
              ▼
┌─────────────────────────────────────────────────────────────┐
│  💾 DATA LAYER                                               │
│  ┌────────────────────┐       ┌──────────────────┐         │
│  │ PostRepositoryImpl │──────▶│ JsonPlaceholderAPI│         │
│  │  (Implementation)  │       │  (Retrofit API)   │         │
│  └────────────────────┘       └─────────┬────────┘         │
└──────────────────────────────────────────┼─────────────────┘
                                            │
                                            ▼
                            🌐 JSONPlaceholder API
                            https://jsonplaceholder.typicode.com
```

### Step-by-Step Flow Example (Fetching Posts)

1. **User Action**: User opens the app
2. **PostScreen**: Calls `viewModel.fetchAllPosts()`
3. **PostViewModel**: 
   - Sets `isLoading = true`
   - Calls `getAllPostsUseCase()`
4. **GetAllPostsUseCase**: 
   - Validates business rules
   - Calls `repository.getAllPosts()`
5. **PostRepositoryImpl**:
   - Makes API call via Retrofit
   - Converts `PostDto` → `Post` (domain model)
   - Wraps result in `Resource<List<Post>>`
6. **PostViewModel**:
   - Receives `Resource.Success` or `Resource.Error`
   - Updates `_state.value` with new data
7. **PostScreen**:
   - Observes `state` via `collectAsState()`
   - Automatically recomposes with new data
   - Displays posts in `LazyColumn`

---

## 🧩 Key Design Patterns

### 1. **Repository Pattern**
```kotlin
interface PostRepository {  // Domain layer defines contract
    suspend fun getAllPosts(): Resource<List<Post>>
}

class PostRepositoryImpl : PostRepository {  // Data layer implements
    override suspend fun getAllPosts(): Resource<List<Post>> { ... }
}
```
**Why?** Abstracts data source - UI doesn't care if data comes from API, database, or cache.

---

### 2. **Use Case Pattern**
```kotlin
class GetAllPostsUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): Resource<List<Post>> {
        return repository.getAllPosts()
    }
}
```
**Why?** Encapsulates business logic in reusable, testable units.

---

### 3. **Resource Wrapper**
```kotlin
sealed class Resource<T> {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>()
    class Loading<T> : Resource<T>()
}
```
**Why?** Type-safe error handling without try-catch everywhere.

---

### 4. **MVVM with StateFlow**
```kotlin
class PostViewModel : ViewModel() {
    private val _state = MutableStateFlow(PostState())
    val state: StateFlow<PostState> = _state.asStateFlow()
    
    fun fetchAllPosts() {
        viewModelScope.launch {
            when (val result = getAllPostsUseCase()) {
                is Resource.Success -> _state.update { ... }
                is Resource.Error -> _state.update { ... }
            }
        }
    }
}
```
**Why?** Survives configuration changes, reactive UI updates, clean separation.

---

## 🚀 How to Run

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Android SDK 24+ (minimum)
- Internet connection (for API calls)

### Steps
1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd JsonPlaceholderPractice
   ```

2. **Open in Android Studio**
   - File → Open → Select project folder

3. **Sync Gradle**
   - Android Studio will auto-sync dependencies
   - Or manually: File → Sync Project with Gradle Files

4. **Run the app**
   - Click ▶️ Run button
   - Select emulator or physical device (API 24+)

5. **Test API calls**
   - App will automatically fetch posts on launch
   - Use UI buttons to create/update/delete posts

---

## 📚 API Endpoints Used

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/posts` | Get all posts |
| GET | `/posts/{id}` | Get single post |
| POST | `/posts` | Create new post |
| PUT | `/posts/{id}` | Update post |
| DELETE | `/posts/{id}` | Delete post |

**Base URL**: `https://jsonplaceholder.typicode.com/`

---

## 🎓 Learning Outcomes

By studying this project, you'll learn:

✅ **Retrofit Integration**: Making HTTP requests in Android  
✅ **Clean Architecture**: Organizing code into layers  
✅ **MVVM Pattern**: Separating UI from business logic  
✅ **Kotlin Coroutines**: Async programming without callbacks  
✅ **StateFlow**: Reactive state management  
✅ **Jetpack Compose**: Modern declarative UI  
✅ **Use Cases**: Implementing business logic  
✅ **Error Handling**: Using sealed classes for type-safe results  
✅ **DTO Pattern**: Separating API models from domain models  

---

## 🔮 Future Enhancements

- [ ] Add **Hilt** for Dependency Injection
- [ ] Implement **Room Database** for offline caching
- [ ] Add **Navigation Component** for multi-screen app
- [ ] Implement **Pull-to-Refresh** with Accompanist
- [ ] Add **Unit Tests** for ViewModels and Use Cases
- [ ] Add **UI Tests** with Compose Testing
- [ ] Implement **Pagination** for large post lists
- [ ] Add **Search** functionality
- [ ] Dark/Light theme toggle
- [ ] Add user authentication flow

---

## 📄 License

This project is for educational purposes. Feel free to use it as a reference or template for your own learning.

---

## 🙏 Acknowledgments

- [JSONPlaceholder](https://jsonplaceholder.typicode.com/) - Free fake API for testing
- [Android Developers](https://developer.android.com/) - Official documentation
- [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern UI toolkit

---

## 👤 Author

**Hasnath Ahmed Tamim**  
Learning Android Development with Clean Architecture

---

## 📞 Contact

**LinkedIn**: [Hasnath Ahmed Tamim](https://www.linkedin.com/in/hasnath-ahmed-tamim/)

Have questions? Feel free to reach out or open an issue!

---

**⭐ If you find this project helpful, please consider giving it a star!**
