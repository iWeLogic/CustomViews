package com.iwelogic.custom.views.ui.screens.chart

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
import com.iwelogic.custom.views.ui.screens.progress.oneDigitsAfterDot
import kotlin.math.abs


@OptIn(ExperimentalTextApi::class, ExperimentalComposeUiApi::class)
@Composable
fun Chart(
    modifier: Modifier,
    values: List<ValueContainer>,
    countXAxis: Int = 8,
    countYAxis: Int = 9
) {
    val lastTouch = remember {
        mutableStateOf(TouchPoint(0.0f, 0.0f))
    }

    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = modifier
        .pointerInteropFilter { motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastTouch.value = TouchPoint(motionEvent.y, motionEvent.x)
                }
                MotionEvent.ACTION_UP -> {
                    lastTouch.value = TouchPoint(motionEvent.y, motionEvent.x)
                }
                MotionEvent.ACTION_MOVE -> {
                    lastTouch.value = TouchPoint(motionEvent.y, motionEvent.x)
                }
            }
            true
        }) {


        val offsetLeft = 50.dp.toPx()
        val offsetBottom = 30.dp.toPx()

        val width = size.width - offsetLeft - 20.dp.toPx()
        val height = size.height - offsetBottom

        val xStep = width / countXAxis
        val yStep = height / countYAxis


        val valuesMax = values.maxOf { it.y }
        val valuesMin = values.minOf { it.y }

        val difference = valuesMax - valuesMin

        val chartMax = valuesMax + 0.1f * difference
        val chartMin = valuesMin - 0.1f * difference

        val chartHeight = chartMax - chartMin

        val points = mutableListOf<Offset>()

        //calculate points coordinates
        for (i in values.indices) {
            val delta = (values[i].y - chartMin)
            val pointHeightPercentage = if (delta == 0.0f) 0.0f else delta / chartHeight
            points.add(Offset(offsetLeft + xStep * i + 1f, y = height * (1.0f - pointHeightPercentage)))
        }

        // draw x lines
        for (i in 0..countXAxis) {
            drawLine(
                start = Offset(x = offsetLeft + xStep * i, y = 0f),
                end = Offset(x = offsetLeft + xStep * i, y = height),
                color = if (i == 0) Color.Black else Color.Gray,
                strokeWidth = (if (i == 0) 2.dp else 1.dp).toPx()
            )
        }

        // draw y lines
        for (i in 0..countYAxis) {
            drawLine(
                start = Offset(x = offsetLeft, y = yStep * i),
                end = Offset(x = offsetLeft + width, y = yStep * i),
                color = if (i == countYAxis) Color.Black else Color.Gray,
                strokeWidth = (if (i == countYAxis) 2.dp else 1.dp).toPx()
            )
        }

        val textSize = 10.dp.toPx()

        //draw y values
        for (i in 1 until countYAxis) {
            drawText(
                topLeft = Offset(
                    x = 10.dp.toPx(),
                    y = size.height - offsetBottom - yStep * i - textSize
                ),
                textMeasurer = textMeasurer,
                text = (chartHeight / countYAxis * i + chartMin).oneDigitsAfterDot()
            )
        }

        //draw x values
        for (i in 1 until countXAxis) {

            val offset = Offset(
                x = offsetLeft - 15.dp.toPx() + i * xStep,
                y = size.height - offsetBottom
            )

            drawText(
                topLeft = offset,
                textMeasurer = textMeasurer,
                text = "${i * 3}:00"
            )
        }

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

        for (i in points.indices) {

            val selected = abs(lastTouch.value.x - points[i].x) < 40f && abs(lastTouch.value.y - points[i].y) < 40f

            if (selected) {

                drawText(
                    topLeft = Offset(
                        x = points[i].x - 10.dp.toPx(),
                        y = points[i].y - 30.dp.toPx()
                    ),
                    textMeasurer = textMeasurer,
                    text = "${values[i].y}"
                )

            }

            drawPoints(
                points = listOf(points[i]),
                strokeWidth = (if (selected) 16.dp else 8.dp).toPx(),
                pointMode = PointMode.Points,
                color = if (selected) Color.Blue else Color.Black
            )
        }
    }
}

data class ValueContainer(
    val y: Float = 0.0f,
    val x: Int = 0
)

data class TouchPoint(
    val y: Float = 0.0f,
    val x: Float = 0.0f
)
