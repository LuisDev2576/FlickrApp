package com.proyect.flickrapp.presentation.navigation

sealed class Routes(val routes: String){

    object PopularPhotosScreen: Routes("popularPhotosScreen")

    fun withArgs(vararg args: String) :String {
        return buildString {
            append(routes)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
