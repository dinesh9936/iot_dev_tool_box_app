package com.ramasofts.iotdevtoolbox.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramasofts.iotdevtoolbox.presentation.components.blescreen.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BleConnectedDeviceScreen(
    navController: NavController,
    bleDevice: BleDevice,
    isConnected: Boolean,
    onToggleConnection: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = bleDevice.name ?: "BLE Device",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF002D72)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Rounded.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF002D72)
                        )
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {

                        StatusDot(isConnected)

                        Spacer(modifier = Modifier.width(8.dp))

                        val color =
                            if (isConnected) Color(0xFFDC2626) else Color(0xFF0056D2)

                        TextButton(
                            onClick = onToggleConnection,
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(1.dp, color),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color(0xFFEFF6FF)
                            ),
                            contentPadding = PaddingValues(
                                horizontal = 12.dp,
                                vertical = 4.dp
                            )
                        ) {
                            Text(
                                text = if (isConnected) "DISCONNECT" else "CONNECT",
                                color = color
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF8FAFC)
                )
            )
        },
        containerColor = Color(0xFFF8FAFC)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {

            // 🔹 TOP CONTENT (takes remaining space)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // 👈 IMPORTANT
            ) {
                Text(text = "Connected to: ${bleDevice.name ?: "Unknown Device"}")
                Text(text = "Address: ${bleDevice.address}")
                Text(text = "RSSI: ${bleDevice.rssi} dBm")
            }

            // 🔹 TERMINAL (BOTTOM FIXED)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0))
            ) {
                Column {

                    // Header
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "TERMINAL",
                            style = MaterialTheme.typography.titleSmall
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "CLEAR",
                            color = Color(0xFFDC2626),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    // Logs
                    BleScannerTerminal(
                        logs = listOf(
                            BleDeviceLog(INFO, "Connected to device"),
                            BleDeviceLog(SCAN, "Discovering services..."),
                            BleDeviceLog(FOUND, "Service UUID found"),
                            BleDeviceLog(WRITE, "Low signal strength"),
                            BleDeviceLog(NOTIFY, "Low signal strength"),
                        ),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun StatusDot(isConnected: Boolean) {
    val color = if (isConnected) Color(0xFF3B82F6) else Color(0xFFEF4444)

    Box(
        modifier = Modifier
            .size(10.dp)
            .background(color, CircleShape)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowPreview() {
    BleConnectedDeviceScreen(
        navController = rememberNavController(),
        bleDevice = BleDevice(
            "NORDIC_UART",
            "00:11:22:33:44:55",
            -70
        ),
        isConnected = true,
        onToggleConnection = {}
    )
}