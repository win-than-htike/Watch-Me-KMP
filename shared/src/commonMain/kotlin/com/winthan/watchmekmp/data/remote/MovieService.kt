package com.winthan.watchmekmp.data.remote

import com.winthan.watchmekmp.data.Constants
import com.winthan.watchmekmp.data.model.MovieDto
import com.winthan.watchmekmp.data.model.MovieResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

/**
 * This class is responsible for fetching movie data from the remote API.
 * It extends the KtorApi class to use its HTTP client.
 */
internal class MovieService : KtorApi() {

    /**
     * Fetches a list of popular movies from the remote API.
     * @param page The page number to fetch. Default is 1.
     * @return A MovieResponse object containing the fetched movies.
     */
    suspend fun getPopularMovies(
        page: Int = 1
    ): MovieResponse = getMovieList(Constants.Route.POPULAR, page)

    /**
     * Fetches a list of now playing movies from the remote API.
     * @param page The page number to fetch. Default is 1.
     * @return A MovieResponse object containing the fetched movies.
     */
    suspend fun getNowPlayingMovies(
        page: Int = 1
    ): MovieResponse = getMovieList(Constants.Route.NOW_PLAYING, page)

    /**
     * Fetches a list of top rated movies from the remote API.
     * @param page The page number to fetch. Default is 1.
     * @return A MovieResponse object containing the fetched movies.
     */
    suspend fun getTopRatedMovies(
        page: Int = 1
    ): MovieResponse = getMovieList(Constants.Route.TOP_RATED, page)

    /**
     * Fetches a list of upcoming movies from the remote API.
     * @param page The page number to fetch. Default is 1.
     * @return A MovieResponse object containing the fetched movies.
     */
    suspend fun getUpcomingMovies(
        page: Int = 1
    ): MovieResponse = getMovieList(Constants.Route.UPCOMING, page)

    /**
     * Fetches a specific movie from the remote API.
     * @param movieId The ID of the movie to fetch.
     * @return A MovieDto object containing the fetched movie data.
     */
    suspend fun getMovie(
        movieId: Int
    ): MovieDto {
        return client.get {
            pathUrl("movie/$movieId")
        }.body()
    }

    /**
     * Fetches a list of movies from the remote API.
     * @param endpoint The API endpoint to fetch from.
     * @param page The page number to fetch. Default is 1.
     * @return A MovieResponse object containing the fetched movies.
     */
    private suspend fun getMovieList(endpoint: String, page: Int = 1): MovieResponse =
        client.get {
            pathUrl(endpoint)
            parameter("page", page)
        }.body()

}