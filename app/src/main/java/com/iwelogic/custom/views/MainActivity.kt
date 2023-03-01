package com.iwelogic.custom.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iwelogic.custom.views.ui.MainScreen
import com.iwelogic.custom.views.ui.chart.ChartScreen
import com.iwelogic.custom.views.ui.navigation.Routes
import com.iwelogic.custom.views.ui.screens.progress.ProgressScreen
import com.iwelogic.custom.views.ui.screens.picker.PickerScreen
import com.iwelogic.custom.views.ui.theme.CustomViewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomViewsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.MAIN.route) {
                    composable(Routes.MAIN.route) {
                        MainScreen(navController)
                    }
                    composable(Routes.PROGRESS.route) {
                        ProgressScreen()
                    }
                    composable(Routes.PICKER.route) {
                        PickerScreen()
                    }
                    composable(Routes.CHART.route) {
                        ChartScreen()
                    }
                }
            }
        }
    }
}