package com.winthan.watchmekmp.di

import com.winthan.watchmekmp.data.remote.MovieService
import com.winthan.watchmekmp.data.remote.MovieRemoteDataSource
import com.winthan.watchmekmp.data.repository.MovieRepositoryImpl
import com.winthan.watchmekmp.domain.repository.MovieRepository
import com.winthan.watchmekmp.domain.usecase.GetMovieUseCase
import com.winthan.watchmekmp.domain.usecase.GetNowPlayingMovieUseCase
import com.winthan.watchmekmp.domain.usecase.GetPopularMovieUseCase
import com.winthan.watchmekmp.domain.usecase.GetTopRatedMovieUseCase
import com.winthan.watchmekmp.domain.usecase.GetTrailerUseCase
import com.winthan.watchmekmp.domain.usecase.GetUpcomingMovieUseCase
import com.winthan.watchmekmp.domain.usecase.HomeScreenUseCase
import com.winthan.watchmekmp.utils.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory { MovieRemoteDataSource(get(), get()) }
    factory { MovieService() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { GetPopularMovieUseCase() }
    factory { GetMovieUseCase() }
    factory { GetNowPlayingMovieUseCase() }
    factory { GetTopRatedMovieUseCase() }
    factory { GetUpcomingMovieUseCase() }
    factory { HomeScreenUseCase(get(), get(), get(), get()) }
    factory { GetTrailerUseCase() }
}

private val sharedModule = listOf(dataModule, utilityModule, domainModule)

fun getSharedModules() = sharedModule