package com.proyect.flickrapp.presentation.popularPhotos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.proyect.flickrapp.domain.model.Photo
import com.proyect.flickrapp.presentation.popularPhotos.componentes.LoadingItem
import com.proyect.flickrapp.presentation.popularPhotos.componentes.MovieItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
@Composable
fun PopularMoviesScreen(
    navController: NavHostController,
    viewModel: PopularPhotosViewModel = hiltViewModel(),
) {


    val movieListState = viewModel.movieListState.collectAsState().value


    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    var withList by remember { mutableStateOf(listOf<Int>()) }
    var lastTenItems by remember { mutableStateOf(listOf<Photo>()) }
    var isVisible by remember { mutableStateOf(true) }
    val coroutine = rememberCoroutineScope()

    key(movieListState.popularPhotoList){
        lastTenItems = movieListState.popularPhotoList.sortedWith(compareBy({ it.page }, { it.id })).takeLast(10)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
        userScrollEnabled = true
    ) {

        items(10) {
            val height = animateDpAsState(
                targetValue = if(withList.isEmpty()) 250.dp else (withList[it]/2.6256).dp, label = "", animationSpec = tween(1000)
            )

            if(isVisible){
                LoadingItem(brush, height = height.value)
            }

        }


        if (movieListState.popularPhotoList.isNotEmpty()) {
            //TODO Sortear los elementos desde el viewModel o movieListState
            withList = movieListState.popularPhotoList.sortedWith(compareBy({ it.page }, { it.id })).map { it.heightC }.take(10)
            items(movieListState.popularPhotoList.sortedWith(compareBy({ it.page }, { it.id }))) {
                val height = animateDpAsState(
                    targetValue = if(lastTenItems.isEmpty()) 250.dp else (it.heightC/2.6256).dp, label = "", animationSpec = tween(1000)
                )
                MovieItem(
                    movie = it,
                    navHostController = navController,
                    brush = brush,
                    height = height.value
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (movieListState.popularPhotoList.indexOf(it) >= movieListState.popularPhotoList.size - 1 && !movieListState.isLoading) {
                    viewModel.onEvent(MovieListUiEvent.Paginate("Popular"))
                }
            }
            coroutine.launch {
                delay(1000)
                isVisible = false
            }

            items(lastTenItems){
                val height = animateDpAsState(
                    targetValue = if(lastTenItems.isEmpty()) 250.dp else (it.heightC/2.6256).dp, label = "", animationSpec = tween(1000)
                )
                LoadingItem(brush, height = if(movieListState.isLoading) 250.dp else height.value)
            }


        }
    }
}





















