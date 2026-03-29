package com.ramasofts.iotdevtoolbox.presentation.components.blescreen

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GlowingScanIcon(
    color: Color,
    size: Dp = 10.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dot")

    Box(contentAlignment = Alignment.Center) {

        repeat(2) { index ->
            val delay = index * 300

            val scale by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 2.5f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1200, delayMillis = delay),
                    repeatMode = RepeatMode.Restart
                ),
                label = "scale$index"
            )

            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.4f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1200, delayMillis = delay),
                    repeatMode = RepeatMode.Restart
                ),
                label = "alpha$index"
            )

            Box(
                modifier = Modifier
                    .size(size)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .background(color, CircleShape)
            )
        }

        // center dot
        Box(
            modifier = Modifier
                .size(size)
                .background(color, CircleShape)
        )
    }
}

// add preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GlowingScanIconPreview() {
    GlowingScanIcon(
        color = Color(0xFF60A5FA)
    )
}


