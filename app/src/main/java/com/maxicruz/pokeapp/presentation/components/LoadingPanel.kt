package com.maxicruz.pokeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.core.*
import com.maxicruz.pokeapp.R

@Composable
fun LoadingPanel() {
    val infiniteTransition = rememberInfiniteTransition(label = "rotateLogo")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotationAnim"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokeball),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxSize(0.2f)
                .rotate(rotation)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPanelPreview() {
    LoadingPanel()
}
