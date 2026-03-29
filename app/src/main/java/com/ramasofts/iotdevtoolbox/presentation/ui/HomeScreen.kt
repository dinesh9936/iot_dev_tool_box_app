package com.ramasofts.iotdevtoolbox.presentation.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ramasofts.iotdevtoolbox.R
import com.ramasofts.iotdevtoolbox.presentation.components.homescreen.HomeToolCard
import com.ramasofts.iotdevtoolbox.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "DevToolBox",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        color = Color(0xFF0056D2)
                    )
                },
                colors = TopAppBarColors(
                    containerColor = Color(0xFFF8FAFC),
                    scrolledContainerColor = Color(0xFFF8FAFC),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF8FAFC)

    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(padding)
        ) {
            val homeMenuData = listOf(
                HomeMenuData(R.drawable.ic_ble, "BLE"),
                HomeMenuData(R.drawable.ic_mqtt, "MQTT"),
                HomeMenuData(R.drawable.ic_websocket, "WebSocket"),
                HomeMenuData(R.drawable.ic_router, "Router"),
                HomeMenuData(R.drawable.ic_usb, "USB Serial"),
                HomeMenuData(R.drawable.ic_tcp, "TCP"),
                HomeMenuData(R.drawable.ic_http, "HTTP/HTTPS")
            )

            items(homeMenuData) { item ->
                HomeToolCard(
                    icon = item.icon,
                    title = item.title,
                    onClick = {
                        when (item.title) {
                            "BLE" -> navController.navigate(Screen.Ble.route)
                        }
                    }
                )

            }
        }
    }
}

data class HomeMenuData(
    val icon: Int,
    val title: String
)


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BleCardPreview() {
    HomeScreen(navController = NavController(LocalContext.current))
}
