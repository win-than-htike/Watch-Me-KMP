package com.winthan.watchmekmp.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winthan.watchmekmp.domain.model.Movie
import com.winthan.watchmekmp.domain.usecase.HomeScreenUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeScreenUseCase: HomeScreenUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeScreenState())
        private set

    private var currentPage = 1

    init {
        loadMovies()
    }

    private fun loadMovies() {
        loadMovieType(MovieType.POPULAR)
        loadMovieType(MovieType.NOW_PLAYING)
        loadMovieType(MovieType.TOP_RATED)
        loadMovieType(MovieType.UPCOMING)
    }

    private fun loadMovieType(movieType: MovieType) {
        viewModelScope.launch {
            updateUiStateLoading(movieType, true)
            try {
                val result = when (movieType) {
                    MovieType.POPULAR -> homeScreenUseCase.getPopularMovieUseCase(currentPage)
                    MovieType.NOW_PLAYING -> homeScreenUseCase.getNowPlayingMovieUseCase(currentPage)
                    MovieType.TOP_RATED -> homeScreenUseCase.getTopRatedMovieUseCase(currentPage)
                    MovieType.UPCOMING -> homeScreenUseCase.getUpcomingMovieUseCase(currentPage)
                }
                updateUiStateSuccess(movieType, result)
            } catch (e: Exception) {
                updateUiStateError(movieType, e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun updateUiStateLoading(movieType: MovieType, isLoading: Boolean) {
        uiState = when (movieType) {
            MovieType.POPULAR -> uiState.copy(isPopularMovieLoading = isLoading)
            MovieType.NOW_PLAYING -> uiState.copy(isNowPlayingMovieLoading = isLoading)
            MovieType.TOP_RATED -> uiState.copy(isTopRatedMovieLoading = isLoading)
            MovieType.UPCOMING -> uiState.copy(isUpcomingMovieLoading = isLoading)
        }
    }

    private fun updateUiStateSuccess(movieType: MovieType, movies: List<Movie>) {
        uiState = when (movieType) {
            MovieType.POPULAR -> uiState.copy(isPopularMovieLoading = false, popularMovies = movies)
            MovieType.NOW_PLAYING -> uiState.copy(isNowPlayingMovieLoading = false, nowPlayingMovies = movies)
            MovieType.TOP_RATED -> uiState.copy(isTopRatedMovieLoading = false, topRatedMovies = movies)
            MovieType.UPCOMING -> uiState.copy(isUpcomingMovieLoading = false, upcomingMovies = movies)
        }
    }

    private fun updateUiStateError(movieType: MovieType, errorMessage: String) {
        uiState = when (movieType) {
            MovieType.POPULAR -> uiState.copy(isPopularMovieLoading = false, popularMovieErrorMessage = errorMessage)
            MovieType.NOW_PLAYING -> uiState.copy(isNowPlayingMovieLoading = false, nowPlayingMovieErrorMessage = errorMessage)
            MovieType.TOP_RATED -> uiState.copy(isTopRatedMovieLoading = false, topRatedMovieErrorMessage = errorMessage)
            MovieType.UPCOMING -> uiState.copy(isUpcomingMovieLoading = false, upcomingMovieErrorMessage = errorMessage)
        }
    }

    enum class MovieType {
        POPULAR, NOW_PLAYING, TOP_RATED, UPCOMING
    }


}

data class HomeScreenState(
    var isPopularMovieLoading: Boolean = false,
    var popularMovies: List<Movie> = emptyList(),
    var popularMovieErrorMessage: String? = null,
    var isNowPlayingMovieLoading: Boolean = false,
    var nowPlayingMovies: List<Movie> = emptyList(),
    var nowPlayingMovieErrorMessage: String? = null,
    var isTopRatedMovieLoading: Boolean = false,
    var topRatedMovies: List<Movie> = emptyList(),
    var topRatedMovieErrorMessage: String? = null,
    var isUpcomingMovieLoading: Boolean = false,
    var upcomingMovies: List<Movie> = emptyList(),
    var upcomingMovieErrorMessage: String? = null
)