package com.iwelogic.custom.views.ui.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChartScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        Chart(
            Modifier
                .height(300.dp)
                .width(300.dp)
                .background(Color.LightGray)
                .align(Alignment.Center),
            ArrayList()
        )
    }
}




