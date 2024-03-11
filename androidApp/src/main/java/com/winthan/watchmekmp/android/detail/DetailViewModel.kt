package com.winthan.watchmekmp.android.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winthan.watchmekmp.domain.model.Movie
import com.winthan.watchmekmp.domain.model.Trailer
import com.winthan.watchmekmp.domain.usecase.GetMovieUseCase
import com.winthan.watchmekmp.domain.usecase.GetTrailerUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieId: Int,
    val getMovieUseCase: GetMovieUseCase,
    val getTrailerUseCase: GetTrailerUseCase
) : ViewModel() {

    var uiState by mutableStateOf(DetailScreenState())
        private set

    init {
        loadMovie()
        loadTrailers()
    }

    private fun loadMovie() {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            uiState = try {
                val movie = getMovieUseCase(movieId)
                uiState.copy(
                    loading = false,
                    movie = movie
                )
            } catch (e: Exception) {
                uiState.copy(
                    loading = false,
                    errorMessage = e.localizedMessage
                )
            }

        }
    }

    private fun loadTrailers() {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            uiState = try {
                val trailers = getTrailerUseCase(movieId)
                uiState.copy(
                    isLoadingTrailer = false,
                    trailers = trailers
                )
            } catch (e: Exception) {
                uiState.copy(
                    isLoadingTrailer = false,
                    errorMessageTrailer = e.localizedMessage
                )
            }

        }
    }

}

data class DetailScreenState(
    var loading: Boolean = false,
    var movie: Movie? = null,
    var errorMessage: String? = null,

    var isLoadingTrailer: Boolean = false,
    var trailers: List<Trailer> = emptyList(),
    var errorMessageTrailer: String? = null
)