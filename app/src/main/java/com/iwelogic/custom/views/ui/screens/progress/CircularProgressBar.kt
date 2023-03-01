package com.iwelogic.custom.views.ui.screens.progress

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressBar(
    progress: Float,
    radius: Dp = 80.dp,
    width: Dp = 20.dp,
    color: Color = Color.Green,
    fontSize: TextUnit = 34.sp,
    animDuration: Int = 1000
) {
    var isAnimationPlayed by remember {
        mutableStateOf(false)
    }
    val currentProgress = animateFloatAsState(
        targetValue = if (isAnimationPlayed) progress else 0.0f,
        animationSpec = tween(
            durationMillis = animDuration,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(key1 = true) {
        isAnimationPlayed = true
    }

    Box(
        modifier = Modifier.size(radius * 2f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {

            drawCircle(
                color = Color.LightGray,
                style = Stroke(width = width.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 3.6f * currentProgress.value,
                useCenter = false,
                style = Stroke(width = width.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = "${currentProgress.value.oneDigitsAfterDot()}%",
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

fun Float?.orZero() = this ?: 0.0

fun Float?.oneDigitsAfterDot() = String.format("%.1f", this.orZero())