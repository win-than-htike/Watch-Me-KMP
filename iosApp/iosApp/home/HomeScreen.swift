import SwiftUI
import shared

struct HomeScreen: View {
    
    @StateObject var viewModel = HomeViewModel()
    
	var body: some View {
		
        NavigationStack {
            
            ScrollView{
                
                Text("Now Playing")
                    .font(.headline)
                    .fontWeight(.bold)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.leading)
                    .frame(alignment: .top)
                    
                
                ScrollView(.horizontal, showsIndicators: false) {
                 
                    LazyHStack(
                        alignment: .top
                    ){
                      
                        ForEach(viewModel.uiState.nowPlayingMovies, id: \.id) { movie in
                            
                            NavigationLink(value: movie) {
                             
                                MovieItem(movie: movie)
                                
                            }
                        }
                        
                    }
                    
                }.padding(.leading)
                
                Text("Popular")
                    .font(.headline)
                    .fontWeight(.bold)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.leading)
                    .frame(alignment: .top)
                    
                
                ScrollView(.horizontal, showsIndicators: false) {
                 
                    LazyHStack(
                        alignment: .top
                    ){
                      
                        ForEach(viewModel.uiState.popularMovies, id: \.id) { movie in
                            
                            NavigationLink(value: movie) {
                             
                                MovieItem(movie: movie)
                                
                            }
                        }
                        
                    }
                    
                }.padding(.leading)
                
                Text("Top Rated")
                    .font(.headline)
                    .fontWeight(.bold)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.leading)
                    .frame(alignment: .top)
                    
                
                ScrollView(.horizontal, showsIndicators: false) {
                 
                    LazyHStack(
                        alignment: .top
                    ){
                      
                        ForEach(viewModel.uiState.topRatedMovies, id: \.id) { movie in
                            
                            NavigationLink(value: movie) {
                             
                                MovieItem(movie: movie)
                                
                            }
                        }
                        
                    }
                    
                }.padding(.leading)
                
                Text("Upcoming")
                    .font(.headline)
                    .fontWeight(.bold)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.leading)
                    .frame(alignment: .top)
                    
                
                ScrollView(.horizontal, showsIndicators: false) {
                 
                    LazyHStack(
                        alignment: .top
                    ){
                      
                        ForEach(viewModel.uiState.upcomingMovies, id: \.id) { movie in
                            
                            NavigationLink(value: movie) {
                             
                                MovieItem(movie: movie)
                                
                            }
                        }
                        
                    }
                    
                }.padding(.leading)
                
            }
            .navigationTitle("Movies")
            .navigationDestination(for: Movie.self) { movie in
                    DetailScreen(movie: movie)
                }
            
        }.task {
            viewModel.loadMovies()
        }
        
	}
}

struct HomeScreen_Previews: PreviewProvider {
	static var previews: some View {
		HomeScreen()
	}
}
