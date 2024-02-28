package com.winthan.watchmekmp.domain.usecase

data class HomeScreenUseCase(
    val getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
    val getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    val getPopularMovieUseCase: GetPopularMovieUseCase
)