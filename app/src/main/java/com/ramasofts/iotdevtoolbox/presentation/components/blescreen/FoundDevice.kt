package com.ramasofts.iotdevtoolbox.presentation.components.blescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramasofts.iotdevtoolbox.R

@Composable
fun FoundDevice(
    icon: Int,
    deviceName: String,
    deviceAddress: String,
    rssi: Int,
    onConnect: () -> Unit,
) {
    Card(
        onClick = onConnect,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(0.5.dp, Color(0xFFE2E8F0)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {

            // 🔹 Icon
            Icon(
                painter = painterResource(id = icon),
                contentDescription = deviceName,
                tint = Color(0xFF2563EB),
                modifier = Modifier
                    .background(
                        Color(0xFFEFF6FF),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp)
            )

            // 🔹 Device Info
            Column(
                modifier = Modifier
                    .weight(1f) // 👈 VERY IMPORTANT
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = deviceName,
                    color = Color(0xFF0F172A),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = deviceAddress,
                    color = Color(0xFF64748B),
                    style = MaterialTheme.typography.bodySmall
                )

                // 📶 RSSI (NEW)
                val rssiColor = when {
                    rssi >= -50 -> Color(0xFF16A34A)
                    else-> Color(0xFFDC2626)
                }

                Text(
                    text = "RSSI: $rssi dBm",
                    color = rssiColor,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // 🔹 Connect Button
            Button(
                onClick = onConnect,
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF1F5F9)
                )
            ) {
                Text(
                    text = "Connect",
                    color = Color(0xFF0056D2),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
// show preview
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FoundDevicePreview() {
    FoundDevice(
        icon = R.drawable.ic_ble,
        deviceName = "BLE Device",
        deviceAddress = "00:11:22:33:44:55",
        rssi = -50,
        onConnect = {},
    )
}
