package com.proyect.flickrapp.presentation.popularPhotos.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingItem(brush: Brush, paddingValues: PaddingValues = PaddingValues(8.dp), height: Dp = 250.dp){

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(paddingValues)
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(22.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(RoundedCornerShape(22.dp))
                .background(brush),
            contentAlignment = Alignment.Center
        ) {}
    }
}