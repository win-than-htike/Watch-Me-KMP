//
//  DetailViewModel.swift
//  iosApp
//
//  Created by Win Than Htike on 28/2/2567 BE.
//  Copyright © 2567 BE orgName. All rights reserved.
//

import Foundation
import shared

struct DetailScreenState {
    var isLoadingTrailer = false
    var trailers: [Trailer] = []
    var errorMessageTrailer: String? = nil
}

@MainActor
class DetailViewModel : ObservableObject {

    private var getTrailerUseCase = GetTrailerUseCase.init()
    
    @Published var uiState = DetailScreenState()
    
    func getTrailers(movieId: Int32) {
        Task {
            do {
                let result: [Trailer] = try await getTrailerUseCase.invoke(movieId: movieId)
                DispatchQueue.main.async {
                    self.uiState.isLoadingTrailer = false
                    self.uiState.trailers = result
                }
            } catch {
                DispatchQueue.main.async {
                    self.uiState.isLoadingTrailer = false
                    self.uiState.errorMessageTrailer = error.localizedDescription
                }
            }
        }
    }
    
}
