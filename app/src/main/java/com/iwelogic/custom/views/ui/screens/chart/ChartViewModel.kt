package com.iwelogic.custom.views.ui.screens.chart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ChartViewModel : ViewModel() {

    var state by mutableStateOf(ChartState())
        private set
}