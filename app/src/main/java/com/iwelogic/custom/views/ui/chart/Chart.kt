package com.iwelogic.custom.views.ui.chart

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalTextApi::class, ExperimentalComposeUiApi::class)
@Composable
fun Chart(
    modifier: Modifier,
    values: List<Point>
) {
    val startDate = System.currentTimeMillis() - 10000
    val endDate = System.currentTimeMillis()

    val yStart = 110
    val yEnd = 200

    val lastTouchY = remember {
        mutableStateOf(0.0f)
    }

    val lastTouchX = remember {
        mutableStateOf(0.0f)
    }

    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = modifier.pointerInteropFilter { motionEvent ->

        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX.value = motionEvent.x
            }
            MotionEvent.ACTION_UP -> {
                lastTouchX.value = motionEvent.x
            }
        }
        true
    }) {

        val padding = 30.dp.toPx()

        drawLine(
            start = Offset(x = padding, y = size.height - padding),
            end = Offset(x = size.width, y = size.height - padding),
            color = Color.Blue,
            strokeWidth = 2.dp.toPx()
        )

        drawLine(
            start = Offset(x = 0 + padding, y = 0f),
            end = Offset(x = 0 + padding, y = size.height - padding),
            color = Color.Blue,
            strokeWidth = 2.dp.toPx()
        )

        for (i in 0..10) {
            if (i == 0) continue
            val delays = (size.height - padding) / 11 * i

            drawText(
                topLeft = Offset(x = padding / 3, y = size.height - padding - 12.dp.toPx() - delays),
                textMeasurer = textMeasurer,
                text = "$i"
            )
        }

        for (i in 0..10) {

            if (i == 0) continue
            val delays = (size.height - padding) / 11 * i

            drawText(
                topLeft = Offset(x = padding + delays, y = size.height - padding),
                textMeasurer = textMeasurer,
                text = "$i"
            )
        }

        val deltaY = size.height - padding
        val deltaX = size.width - padding

        val points = listOf(
            Offset(padding + deltaX * 0.1f, y = padding + deltaY * 0.8f),
            Offset(padding + deltaX * 0.2f, y = padding + deltaY * 0.8f),
            Offset(padding + deltaX * 0.3f, y = padding + deltaY * 0.4f),
            Offset(padding + deltaX * 0.4f, y = padding + deltaY * 0.7f),
            Offset(padding + deltaX * 0.5f, y = padding + deltaY * 0.3f),
            Offset(padding + deltaX * 0.6f, y = padding + deltaY * 0.1f),
            Offset(padding + deltaX * 0.7f, y = padding + deltaY * 0.7f),
            Offset(padding + deltaX * 0.8f, y = padding + deltaY * 0.6f),
            Offset(padding + deltaX * 0.9f, y = padding + deltaY * 0.3f),
        )

        Log.w("myLog", "_Chart: ${lastTouchX.value}")

        drawPoints(
            points = points,
            strokeWidth = 10.dp.toPx(),
            pointMode = PointMode.Points,
            color = if (lastTouchX.value > 300f) Color.Blue else Color.Green
        )

        for (i in points.indices) {
            if (i < points.size - 1) {
                drawLine(
                    start = points[i],
                    end = points[i + 1],
                    color = Color.Green,
                    strokeWidth = 2.dp.toPx()
                )
            }
        }
    }
}

data class Point(
    val x: Int = 0,
    val y: Int = 0
)
