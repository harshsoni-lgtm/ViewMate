@file:OptIn(androidx.tv.material3.ExperimentalTvMaterial3Api::class)

package com.viewmate.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.Card
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ViewMateApp()
        }
    }
}

data class Movie(
    val title: String,
    val genre: String,
    val description: String,
    val rating: String,
    val accent: Color
)

private val recommendedMovies = listOf(
    Movie(
        "Interstellar",
        "Sci-Fi • Adventure",
        "A journey beyond the stars.",
        "8.7",
        Color(0xFF243B55)
    ),
    Movie(
        "Inception",
        "Sci-Fi • Thriller",
        "Reality is only the beginning.",
        "8.8",
        Color(0xFF42275A)
    ),
    Movie(
        "Dune",
        "Sci-Fi • Epic",
        "The future belongs to those who control it.",
        "8.5",
        Color(0xFF8E6B3E)
    ),
    Movie(
        "The Dark Knight",
        "Action • Crime",
        "Some heroes are not born.",
        "9.0",
        Color(0xFF202020)
    ),
    Movie(
        "Blade Runner 2049",
        "Sci-Fi • Mystery",
        "What makes us human?",
        "8.0",
        Color(0xFF314755)
    )
)

private val actionMovies = listOf(
    Movie(
        "Mad Max",
        "Action • Adventure",
        "Hope survives the wasteland.",
        "8.1",
        Color(0xFF7B3F00)
    ),
    Movie(
        "Gladiator",
        "Action • Drama",
        "Strength. Honor. Vengeance.",
        "8.5",
        Color(0xFF6B4F2A)
    ),
    Movie(
        "John Wick",
        "Action • Thriller",
        "He is back.",
        "7.4",
        Color(0xFF17202A)
    ),
    Movie(
        "Top Gun",
        "Action • Drama",
        "Feel the need for speed.",
        "8.3",
        Color(0xFF24527A)
    ),
    Movie(
        "The Batman",
        "Action • Mystery",
        "The night belongs to him.",
        "7.8",
        Color(0xFF151515)
    )
)

private val familyMovies = listOf(
    Movie(
        "The Incredibles",
        "Animation • Family",
        "A family with extraordinary abilities.",
        "8.0",
        Color(0xFFB3261E)
    ),
    Movie(
        "Toy Story",
        "Animation • Family",
        "Adventure is waiting around the corner.",
        "8.3",
        Color(0xFF2E5D8C)
    ),
    Movie(
        "Paddington",
        "Family • Comedy",
        "A little bear with a big heart.",
        "7.8",
        Color(0xFF8A5A44)
    ),
    Movie(
        "The Lion King",
        "Animation • Adventure",
        "A young lion discovers his destiny.",
        "8.5",
        Color(0xFF8C6A25)
    )
)

@Composable
fun ViewMateApp() {

    var selectedMovie by remember {
        mutableStateOf<Movie?>(null)
    }

    var showAiPicks by remember {
        mutableStateOf(false)
    }

    MaterialTheme {

        when {

            selectedMovie != null -> {
                MovieDetailsScreen(
                    movie = selectedMovie!!,
                    onBack = {
                        selectedMovie = null
                    }
                )
            }

            showAiPicks -> {
                AiPicksScreen(
                    onMovieClick = { movie ->
                        selectedMovie = movie
                    },
                    onBack = {
                        showAiPicks = false
                    }
                )
            }

            else -> {
                HomeScreen(
                    onMovieClick = { movie ->
                        selectedMovie = movie
                    },
                    onAiPicksClick = {
                        showAiPicks = true
                    }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit,
    onAiPicksClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07080C))
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                TopBar(
                    onAiPicksClick = onAiPicksClick
                )
            }

            item {
                HeroSection()
            }

            item {
                MovieSection(
                    title = "Recommended for You",
                    movies = recommendedMovies,
                    onMovieClick = onMovieClick
                )
            }

            item {
                MovieSection(
                    title = "Because You Like Action",
                    movies = actionMovies,
                    onMovieClick = onMovieClick
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(40.dp)
                )
            }
        }
    }
}

@Composable
fun TopBar(
    onAiPicksClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 48.dp,
                vertical = 28.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "VIEWMATE",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.width(30.dp)
            )

            Text(
                text = "Home",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.width(24.dp)
            )

            Text(
                text = "My List",
                color = Color.Gray,
                fontSize = 16.sp
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Button(
                onClick = onAiPicksClick
            ) {
                Text(
                    text = "AI Picks",
                    fontSize = 15.sp
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "⌕  Search",
                color = Color.LightGray,
                fontSize = 16.sp
            )

            Spacer(
                modifier = Modifier.width(25.dp)
            )

            Text(
                text = "●",
                color = Color(0xFF7C4DFF),
                fontSize = 22.sp
            )
        }
    }
}

@Composable
fun HeroSection() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(310.dp)
            .padding(horizontal = 48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF182A3A),
                        Color(0xFF11131B),
                        Color(0xFF08090D)
                    )
                ),
                shape = RoundedCornerShape(18.dp)
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(38.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "FEATURED",
                color = Color(0xFFB7A7FF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "INTERSTELLAR",
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "A journey beyond imagination.",
                color = Color.LightGray,
                fontSize = 18.sp
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Row {

                Button(
                    onClick = {}
                ) {
                    Text(
                        text = "WATCH",
                        fontSize = 17.sp
                    )
                }

                Spacer(
                    modifier = Modifier.width(15.dp)
                )

                Button(
                    onClick = {}
                ) {
                    Text(
                        text = "WHY THIS?",
                        fontSize = 17.sp
                    )
                }
            }
        }
    }

    Spacer(
        modifier = Modifier.height(35.dp)
    )
}

@Composable
fun AiPicksScreen(
    onMovieClick: (Movie) -> Unit,
    onBack: () -> Unit
) {

    var selectedMood by remember {
        mutableStateOf<String?>(null)
    }

    var movies by remember {
        mutableStateOf<List<Movie>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }

    BackHandler {
        onBack()
    }

    LaunchedEffect(selectedMood) {

        val mood = selectedMood
            ?: return@LaunchedEffect

        isLoading = true
        errorMessage = null

        try {

            val response = RetrofitClient.api.getRecommendations(
                RecommendationRequest(mood)
            )

            val allMovies =
                recommendedMovies +
                actionMovies +
                familyMovies

            movies = response.recommendations.mapNotNull { title ->

                allMovies.find { movie ->
                    movie.title == title
                }
            }

        } catch (e: Exception) {

            movies = emptyList()

            errorMessage =
                "Unable to connect to ViewMate AI."
        }

        isLoading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07080C))
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 48.dp,
                end = 48.dp,
                top = 35.dp,
                bottom = 50.dp
            )
        ) {

            item {

                Text(
                    text = "VIEWMATE AI",
                    color = Color(0xFFB7A7FF),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "What are you in the mood for?",
                    color = Color.White,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Tell ViewMate what you feel like watching.",
                    color = Color.LightGray,
                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(28.dp)
                )
            }

            item {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    listOf(
                        "Mind-bending",
                        "Action",
                        "Sci-Fi",
                        "Family"
                    ).forEach { mood ->

                        Button(
                            onClick = {
                                selectedMood = mood
                            }
                        ) {

                            Text(
                                text = mood,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(35.dp)
                )
            }

            if (selectedMood == null) {

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(
                                Color(0xFF11131B),
                                RoundedCornerShape(18.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Choose a mood to get your personalized picks.",
                            color = Color.Gray,
                            fontSize = 20.sp
                        )
                    }
                }

            } else {

                item {

                    Text(
                        text = "ViewMate's Picks for You",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Based on your mood: $selectedMood",
                        color = Color(0xFFB7A7FF),
                        fontSize = 16.sp
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }

                item {

                    when {

                        isLoading -> {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = "ViewMate is finding your picks...",
                                    color = Color.LightGray,
                                    fontSize = 20.sp
                                )
                            }
                        }

                        errorMessage != null -> {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = errorMessage ?: "Something went wrong.",
                                    color = Color(0xFFFF8A80),
                                    fontSize = 20.sp
                                )
                            }
                        }

                        movies.isEmpty() -> {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = "No recommendations found.",
                                    color = Color.Gray,
                                    fontSize = 20.sp
                                )
                            }
                        }

                        else -> {

                            MovieSection(
                                title = "",
                                movies = movies,
                                onMovieClick = onMovieClick
                            )
                        }
                    }
                }
            }

            item {

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Button(
                    onClick = onBack
                ) {

                    Text(
                        text = "BACK",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun MovieSection(
    title: String,
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)
    ) {

        if (title.isNotEmpty()) {

            Text(
                text = title,
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 48.dp
                )
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            contentPadding = PaddingValues(
                horizontal = 48.dp
            )
        ) {

            items(movies) { movie ->

                MovieCard(movie) {
                    onMovieClick(movie)
                }
            }
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit
) {

    var focused by remember {
        mutableStateOf(false)
    }

    val cardWidth by animateDpAsState(
        targetValue = if (focused) 225.dp else 205.dp,
        label = "cardWidth"
    )

    val cardHeight by animateDpAsState(
        targetValue = if (focused) 205.dp else 185.dp,
        label = "cardHeight"
    )

    Card(
        onClick = onClick,
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .onFocusChanged {
                focused = it.isFocused
            }
            .focusable()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            movie.accent,
                            Color(0xFF101117)
                        )
                    )
                )
                .border(
                    width = if (focused) 3.dp else 0.dp,
                    color = if (focused) {
                        Color.White
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(12.dp)
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = movie.genre,
                    color = Color.LightGray,
                    fontSize = 13.sp
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "★ ${movie.rating}",
                    color = Color(0xFFFFD54F),
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun MovieDetailsScreen(
    movie: Movie,
    onBack: () -> Unit
) {

    BackHandler {
        onBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07080C))
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(55.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .width(330.dp)
                    .height(470.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                movie.accent,
                                Color(0xFF101117)
                            )
                        ),
                        RoundedCornerShape(18.dp)
                    )
                    .border(
                        2.dp,
                        Color(0xFF343743),
                        RoundedCornerShape(18.dp)
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(28.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Text(
                        text = movie.title,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = movie.genre,
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "★ ${movie.rating}",
                        color = Color(0xFFFFD54F),
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.width(55.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "MOVIE DETAILS",
                    color = Color(0xFFB7A7FF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Text(
                    text = movie.description,
                    color = Color.LightGray,
                    fontSize = 20.sp
                )

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                Row {

                    Button(
                        onClick = {}
                    ) {

                        Text(
                            text = "WATCH NOW",
                            fontSize = 17.sp
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )

                    Button(
                        onClick = onBack
                    ) {

                        Text(
                            text = "BACK",
                            fontSize = 17.sp
                        )
                    }
                }
            }
        }
    }
}