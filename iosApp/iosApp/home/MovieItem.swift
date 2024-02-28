//
//  MovieItem.swift
//  iosApp
//
//  Created by Win Than Htike on 19/2/2567 BE.
//  Copyright © 2567 BE orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieItem: View {
    
    let movie: Movie
    
    var body: some View {
        VStack(
            alignment: .leading, spacing: 8
        ) {
            
            ZStack {
                
                AsyncImage(url: URL(string: movie.imageUrl)) { image in
                    
                    image.resizable()
                    
                } placeholder: {
                    Color.gray
                }
                
                Circle()
                    .frame(width: 50, height: 50)
                    .foregroundColor(.black.opacity(0.7))
                
                Image(systemName: "play.fill")
                
            }
            .frame(maxWidth: 150, idealHeight: .infinity)
            .clipShape(RoundedRectangle(cornerRadius: 8))
            
            Text(movie.title)
                .font(.title3)
                .fontWeight(.black)
                .lineLimit(1)
            
            Text(movie.releaseDate)
                .font(.caption)
            
        }
        .frame(maxWidth: 150, maxHeight: 260)
    }
}

#Preview {
    MovieItem(
        movie: Movie(id: 1, title: "Title", description: "Description", imageUrl: "imageUrl", releaseDate: "12th Oct 2024")
    )
}
