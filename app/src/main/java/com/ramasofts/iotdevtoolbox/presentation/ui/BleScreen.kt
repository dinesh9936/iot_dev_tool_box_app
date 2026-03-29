package com.ramasofts.iotdevtoolbox.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramasofts.iotdevtoolbox.R
import com.ramasofts.iotdevtoolbox.presentation.components.blescreen.*

data class BleDevice(
    val name: String?,
    val address: String,
    val rssi: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BleScreen(navController: NavController) {

    val allFoundDevices = listOf(
        BleDevice("NORDIC_UART", "00:11:22:33:44:55", -70),
        BleDevice("ESP32_BLE", "66:77:88:99:AA:BB", -80),
        BleDevice("ESP32_BLE", "CC:DD:EE:FF:00:11", -50)
    )

    val logs = listOf(
        BleDeviceLog(INFO, "Bluetooth adapter initialized successfully."),
        BleDeviceLog(SCAN, "Started LE scan with filter: RSSI > -100dBm"),
        BleDeviceLog(FOUND, "Device 00:11:22:33:44:55 (NORDIC_UART) found with -70"),
        BleDeviceLog(ERROR, "Failed to connect to device."),
        BleDeviceLog(WARN, "Low signal strength detected.")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BLE Scanner") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF002D72)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF8FAFC),
                    titleContentColor = Color(0xFF002D72)
                )
            )
        },
        containerColor = Color(0xFFF8FAFC)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(start = 8.dp, top = 4.dp, end = 8.dp)
        ) {

            // 🔹 1. Scan Status Card (TOP)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            GlowingScanIcon(
                                color = Color(0xFF002D72),
                                size = 10.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ACTIVE SCAN",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color(0xFF002D72)
                            )
                        }

                        Text(
                            text = "Scanning for peripherals...",
                            fontSize = 16.sp,
                            fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                            style = MaterialTheme.typography.headlineSmall,
                        )

                        Text(
                            text = "Discovered ${allFoundDevices.size} devices",
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626))
                        ) {
                            Text("Stop Scan", color = Color.White)
                        }
                    }

                    LinearProgressIndicator(
                        progress = { 0.4f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp),
                        color = Color(0xFF60A5FA),
                        trackColor = Color(0xFFE5E7EB),
                        strokeCap = StrokeCap.Round
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 2. Devices List (MIDDLE - FLEXIBLE)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // 👈 takes remaining space
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
            ) {
                Column(modifier = Modifier.padding(8.dp)) {

                    Text(
                        text = "DISCOVERED DEVICES (${allFoundDevices.size})",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    LazyColumn {
                        items(allFoundDevices, key = { it.address }) { device ->
                            FoundDevice(
                                icon = R.drawable.ic_ble,
                                deviceName = device.name ?: "Unknown",
                                deviceAddress = device.address,
                                rssi = device.rssi,
                                onConnect = {}
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 3. Terminal (BOTTOM FIXED)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // 👈 fixed bottom panel
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
            ) {
                Column {

                    // Header
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_terminal),
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "LIVE SCAN LOG",
                            style = MaterialTheme.typography.titleSmall
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = "CLEAR LOG",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Red
                        )
                    }

                    // Terminal logs
                    BleScannerTerminal(
                        logs = logs,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BleScreenPreview() {
    BleScreen(navController = rememberNavController())
}