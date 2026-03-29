package com.ramasofts.iotdevtoolbox.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Ble : Screen("ble")
    object Details : Screen("details")
}