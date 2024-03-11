//
//  DetailScreen.swift
//  iosApp
//
//  Created by Win Than Htike on 19/2/2567 BE.
//  Copyright © 2567 BE orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailScreen: View {
    
    @StateObject var viewModel = DetailViewModel()
    
    let movie: Movie
    
    var body: some View {
        
        ScrollView {
            
            VStack {
                
                ZStack {
                    
                    AsyncImage(url: URL(string: movie.imageUrl)) { image in
                        
                        image.resizable()
                            .scaledToFill()
                        
                    } placeholder: {
                        ProgressView()
                    }
                    
                }
                .frame(maxWidth: .infinity, minHeight: 300, maxHeight: 300)
                
                
                VStack(alignment: .leading, spacing: 12) {
                    
                    Text(movie.title)
                        .font(.title)
                        .fontWeight(.bold)
                        .fixedSize(horizontal: false, vertical: true)
                    
                    
                    Button(action: {}) {
                        
                        HStack {
                            Image(systemName: "play.fill")
                                .tint(.white)
                            
                            Text("Start Watching Now")
                                .foregroundColor(.white)
                        }
                        
                    }
                    .frame(maxWidth: .infinity, maxHeight: 40)
                    .padding()
                    .background(.red)
                    
                    Text("Released in \(movie.releaseDate)".uppercased())
                    
                    Text(movie.description_)
                        .font(.body)
                        .fixedSize(horizontal: false, vertical: true)
                    
                    Text("Trailers")
                    
                    ScrollView(.horizontal, showsIndicators: false) {
                        
                        LazyHStack(
                            alignment: .top
                        ){
                            
                            ForEach(viewModel.uiState.trailers, id: \.id) { trailer in
                                
                                NavigationLink(value: trailer) {
                                    
                                    ZStack {
                                        
                                        AsyncImage(url: URL(string: "https://img.youtube.com/vi/\(trailer.key)/0.jpg")) { image in
                                            image.resizable()
                                        } placeholder: {
                                            Color.gray
                                        }
                                        .aspectRatio(contentMode: .fit)
                                        .frame(width: 300, height: 250)
                                        .clipped()
                                        
                                        VStack {
                                            Spacer()
                                            Text(trailer.name)
                                                .foregroundColor(.white)
                                                .multilineTextAlignment(.center)
                                                .lineLimit(2)
                                                .truncationMode(.tail)
                                                .padding(.all, 8)
                                                .frame(width: 300)
                                        }
                                        
                                        // Play button overlay
                                        Image(systemName: "play.circle.fill") // Using a system image for simplicity
                                            .resizable()
                                            .aspectRatio(contentMode: .fit)
                                            .frame(width: 50, height: 50)
                                            .background(Color.black.opacity(0.6))
                                            .clipShape(Circle())
                                            .foregroundColor(.white)
                                            .onTapGesture {
                                                let appURL = URL(string: "youtube://www.youtube.com/watch?v=\(trailer.key)")!
                                                let webURL = URL(string: "https://www.youtube.com/watch?v=\(trailer.key)")!
                                                
                                                if UIApplication.shared.canOpenURL(appURL) {
                                                    // If YouTube app is installed, open the video in the app
                                                    UIApplication.shared.open(appURL, options: [:], completionHandler: nil)
                                                } else {
                                                    // If YouTube app is not installed, open the video in Safari
                                                    UIApplication.shared.open(webURL, options: [:], completionHandler: nil)
                                                }
                                            }
                                    }
                                    
                                }
                            }
                            
                        }
                        
                    }.padding(.leading)
                    
                    
                }
                .padding(20)
                .background(.black)
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            
        }.task {
            viewModel.getTrailers(movieId: movie.id)
        }
        
    }
}

#Preview {
    DetailScreen(
        movie: Movie(id: 1, title: "Title", description: "Description", imageUrl: "imageUrl", releaseDate: "12th Oct 2024")
    )
}
