package com.iwelogic.custom.views.ui.screens.progress

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProgressViewModel : ViewModel() {

    var state by mutableStateOf(ProgressState())
        private set

    fun onProgressChange(value: Float) {
        state = state.copy(progress = value)
    }
}