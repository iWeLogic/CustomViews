package com.iwelogic.custom.views.ui.navigation

import com.iwelogic.custom.views.R

enum class Routes(val title: String, val icon: Int, val route: String) {
    MAIN("MAIN", 0, "main"),
    PROGRESS("Progress", R.drawable.progress, "progress"),
    PICKER("Picker", R.drawable.picker, "picker"),
    CHART("Chart", R.drawable.picker, "chart")
}
