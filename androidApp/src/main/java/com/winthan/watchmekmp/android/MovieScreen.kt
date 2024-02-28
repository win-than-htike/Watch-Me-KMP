package com.winthan.watchmekmp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.winthan.watchmekmp.android.common.Detail
import com.winthan.watchmekmp.android.common.Home
import com.winthan.watchmekmp.android.common.MovieAppBar
import com.winthan.watchmekmp.android.common.movieDestinations
import com.winthan.watchmekmp.android.detail.DetailScreen
import com.winthan.watchmekmp.android.detail.DetailViewModel
import com.winthan.watchmekmp.android.home.HomeScreen
import com.winthan.watchmekmp.android.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieScreen() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark){
        MaterialTheme.colorScheme.onPrimary
    }else {
        Color.Transparent
    }
    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    }?: Home

    Scaffold(
        topBar = {
            MovieAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {
                navController.navigateUp()
            }
        }
    ) {innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Home.routeWithArgs
        ){
            composable(Home.routeWithArgs){
                val homeViewModel: HomeViewModel = koinViewModel()
                HomeScreen(
                    uiState = homeViewModel.uiState,
                    navigateToMovieDetail = {
                        navController.navigate(
                            "${Detail.route}/${it.id}"
                        )
                    }
                )
            }

            composable(Detail.routeWithArgs, arguments = Detail.arguments){
                val movieId = it.arguments?.getInt("movieId") ?: 0
                val detailViewModel: DetailViewModel = koinViewModel(
                    parameters = {
                        parametersOf(movieId)
                    }
                )
                DetailScreen(
                    uiState = detailViewModel.uiState
                )
            }
        }

    }
}