package com.winthan.watchmekmp.android.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.winthan.watchmekmp.android.R
import com.winthan.watchmekmp.android.Red
import com.winthan.watchmekmp.domain.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    navigateToMovieDetail: (Movie) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            MovieSection(
                title = stringResource(id = R.string.til_now_playing),
                movies = uiState.nowPlayingMovies,
                navigateToMovieDetail = navigateToMovieDetail
            )

            MovieSection(
                title = stringResource(id = R.string.til_popular),
                movies = uiState.popularMovies,
                navigateToMovieDetail = navigateToMovieDetail
            )

            MovieSection(
                title = stringResource(id = R.string.top_rated),
                movies = uiState.topRatedMovies,
                navigateToMovieDetail = navigateToMovieDetail
            )

            MovieSection(
                title = stringResource(id = R.string.upcoming),
                movies = uiState.upcomingMovies,
                navigateToMovieDetail = navigateToMovieDetail
            )

        }

    }

}

@Composable
fun MovieSection(
    title: String, 
    movies: List<Movie>, 
    navigateToMovieDetail: (Movie) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(
            items = movies,
            key = { _, movie -> movie.id }
        ) { _, movie ->
            MovieListItem(movie = movie, onClickedMovie = navigateToMovieDetail)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClickedMovie: (Movie) -> Unit
) {

    Card(
        modifier = modifier
            .width(150.dp)
            .height(220.dp)
            .clickable { onClickedMovie(movie) },
        shape = RoundedCornerShape(8.dp),
    ) {

        Column {

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 2.dp, bottomEnd = 2.dp
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .background(Color.Black.copy(alpha = 0.6f)),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.play_button),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

            }

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }

    }

}