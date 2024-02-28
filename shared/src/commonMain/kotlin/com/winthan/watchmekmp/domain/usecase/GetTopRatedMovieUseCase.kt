package com.winthan.watchmekmp.domain.usecase

import com.winthan.watchmekmp.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetTopRatedMovieUseCase: KoinComponent {

    private val movieRepository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(
        page: Int
    ) = movieRepository.getTopRated(page)

}