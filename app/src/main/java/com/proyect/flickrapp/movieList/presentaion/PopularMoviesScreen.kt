package com.ahmedapps.moviesapp.movieList.presentaion

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ahmedapps.moviesapp.movieList.presentaion.componentes.MovieItem
import com.ahmedapps.moviesapp.movieList.util.Category

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
@Composable
fun PopularMoviesScreen(
    movieListState: MovieListState,
    navController: NavHostController,
    onEvent: (MovieListUiEvent) -> Unit
) {

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

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
        userScrollEnabled = true
    ) {
        if (movieListState.popularMovieList.isEmpty()) {
            items(10) {

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(200.dp)
                        .padding(8.dp)
                        .background(Color.White)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(28.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                            .height(250.dp)
                            .clip(RoundedCornerShape(22.dp))
                            .background(brush),
                        contentAlignment = Alignment.Center
                    ) {}
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp, bottom = 6.dp, start = 20.dp, end = 30.dp)
                            .height(14.dp)
                            .clip(RoundedCornerShape(22.dp))
                            .background(brush),
                        contentAlignment = Alignment.Center
                    ) {}
                }

            }
        }else{
            items(
                movieListState.popularMovieList.sortedWith(
                    compareBy(
                        { it.page },
                        //{ it.id }
                    )
                )
            ) {
                MovieItem(
                    movie = it,
                    navHostController = navController
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = movieListState.popularMovieList.indexOf(it).toString())

                if (movieListState.popularMovieList.indexOf(it) >= movieListState.popularMovieList.size - 1 && !movieListState.isLoading) {
                    onEvent(MovieListUiEvent.Paginate(Category.POPULAR))
                    println("Cargando nuevas fotos")
                }
            }
            items(10) {

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(200.dp)
                        .padding(8.dp)
                        .background(Color.White)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(28.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                            .height(250.dp)
                            .clip(RoundedCornerShape(22.dp))
                            .background(brush),
                        contentAlignment = Alignment.Center
                    ) {}
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp, bottom = 6.dp, start = 20.dp, end = 30.dp)
                            .height(14.dp)
                            .clip(RoundedCornerShape(22.dp))
                            .background(brush),
                        contentAlignment = Alignment.Center
                    ) {}
                }

            }
        }

    }
}





















