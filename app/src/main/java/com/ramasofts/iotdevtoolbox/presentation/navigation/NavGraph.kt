package com.ramasofts.iotdevtoolbox.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramasofts.iotdevtoolbox.presentation.ui.BleScreen
import com.ramasofts.iotdevtoolbox.presentation.ui.HomeScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Ble.route) {
            BleScreen(navController)
        }

        composable(Screen.Details.route) {

        }
    }
}