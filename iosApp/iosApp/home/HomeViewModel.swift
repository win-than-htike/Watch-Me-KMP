//
//  HomeViewModel2.swift
//  iosApp
//
//  Created by Win Than Htike on 21/2/2567 BE.
//  Copyright © 2567 BE orgName. All rights reserved.
//

import Foundation
import shared

enum MovieType {
    case popular, nowPlaying, topRated, upcoming
}

struct HomeScreenState {
    var isPopularMovieLoading = false
    var popularMovies: [Movie] = []
    var popularMovieErrorMessage: String? = nil

    var isNowPlayingMovieLoading = false
    var nowPlayingMovies: [Movie] = []
    var nowPlayingMovieErrorMessage: String? = nil

    var isTopRatedMovieLoading = false
    var topRatedMovies: [Movie] = []
    var topRatedMovieErrorMessage: String? = nil

    var isUpcomingMovieLoading = false
    var upcomingMovies: [Movie] = []
    var upcomingMovieErrorMessage: String? = nil
}


@MainActor
class HomeViewModel: ObservableObject {
    
    private var homeScreenUseCase: HomeScreenUseCase = HomeScreenUseCase.init(
        getUpcomingMovieUseCase: GetUpcomingMovieUseCase(),
        getTopRatedMovieUseCase: GetTopRatedMovieUseCase(),
        getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase(),
        getPopularMovieUseCase: GetPopularMovieUseCase()
    )
    private var currentPage = 1

    @Published var uiState = HomeScreenState()

    func loadMovies() {
        loadMovieType(.popular)
        loadMovieType(.nowPlaying)
        loadMovieType(.topRated)
        loadMovieType(.upcoming)
    }

    private func loadMovieType(_ movieType: MovieType) {
        Task {
            do {
                let result: [Movie] = try await {
                    switch movieType {
                    case .popular:
                        return try await homeScreenUseCase.getPopularMovieUseCase.invoke(page: Int32(currentPage))
                    case .nowPlaying:
                        return try await homeScreenUseCase.getNowPlayingMovieUseCase.invoke(page: Int32(currentPage))
                    case .topRated:
                        return try await homeScreenUseCase.getTopRatedMovieUseCase.invoke(page: Int32(currentPage))
                    case .upcoming:
                        return try await homeScreenUseCase.getUpcomingMovieUseCase.invoke(page: Int32(currentPage))
                    }
                }()
                DispatchQueue.main.async {
                    self.updateUiStateSuccess(movieType, movies: result)
                }
            } catch {
                DispatchQueue.main.async {
                    self.updateUiStateError(movieType, errorMessage: error.localizedDescription)
                }
            }
        }
    }

    private func updateUiStateLoading(_ movieType: MovieType, isLoading: Bool) {
        switch movieType {
        case .popular:
            uiState.isPopularMovieLoading = true
        case .nowPlaying:
            uiState.isNowPlayingMovieLoading = true
        case .topRated:
            uiState.isTopRatedMovieLoading = true
        case .upcoming:
            uiState.isUpcomingMovieLoading = true
        }
    }

    private func updateUiStateSuccess(_ movieType: MovieType, movies: [Movie]) {
        switch movieType {
        case .popular:
            uiState.isPopularMovieLoading = false
            uiState.popularMovies = movies
        case .nowPlaying:
            uiState.isNowPlayingMovieLoading = false
            uiState.nowPlayingMovies = movies
        case .topRated:
            uiState.isTopRatedMovieLoading = false
            uiState.topRatedMovies = movies
        case .upcoming:
            uiState.isUpcomingMovieLoading = false
            uiState.upcomingMovies = movies
        }
    }

    private func updateUiStateError(_ movieType: MovieType, errorMessage: String) {
        switch movieType {
        case .popular:
            uiState.popularMovieErrorMessage = errorMessage
        case .nowPlaying:
            uiState.nowPlayingMovieErrorMessage = errorMessage
        case .topRated:
            uiState.topRatedMovieErrorMessage = errorMessage
        case .upcoming:
            uiState.upcomingMovieErrorMessage = errorMessage
        }
    }
}
