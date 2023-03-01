package com.iwelogic.custom.views.ui.screens.selector

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PickerViewModel : ViewModel() {

    var state by mutableStateOf(PickerState())
        private set
}