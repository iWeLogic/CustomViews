package com.iwelogic.custom.views.ui.screens.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProgressScreen() {
    val viewModel = viewModel<ProgressViewModel>()
    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressBar(progress = state.progress)

            Spacer(modifier = Modifier.height(100.dp))

            Slider(
                modifier = Modifier.semantics { contentDescription = "Localized Description" },
                value = state.progress,
                onValueChange = { viewModel.onProgressChange(it) },
                valueRange = 0f..100f,
            )
        }
    }
}