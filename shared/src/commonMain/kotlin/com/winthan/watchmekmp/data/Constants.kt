package com.winthan.watchmekmp.data

object Constants {

    const val BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY = "PASTE_YOUR_API_KEY"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    object Route {
        const val MOVIE = "movie"
        const val POPULAR = "/movie/popular"
        const val TOP_RATED = "/movie/top_rated"
        const val UPCOMING = "/movie/upcoming"
        const val NOW_PLAYING = "/movie/now_playing"
    }

}