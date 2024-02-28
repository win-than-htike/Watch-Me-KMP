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
                
                    
                }
                .padding(20)
                .background(.black)
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            
        }
        
    }
}

#Preview {
    DetailScreen(
        movie: Movie(id: 1, title: "Title", description: "Description", imageUrl: "imageUrl", releaseDate: "12th Oct 2024")
    )
}
