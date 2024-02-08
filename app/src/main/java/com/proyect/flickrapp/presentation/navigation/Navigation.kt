package com.proyect.flickrapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.proyect.flickrapp.presentation.popularPhotos.PopularMoviesScreen
import com.proyect.flickrapp.presentation.popularPhotos.PopularPhotosViewModel

@Composable
fun Navigation(){

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Routes.PopularPhotosScreen.routes
    ) {
        composable(Routes.PopularPhotosScreen.routes){
            PopularMoviesScreen(navController = navHostController)
        }

    }
}