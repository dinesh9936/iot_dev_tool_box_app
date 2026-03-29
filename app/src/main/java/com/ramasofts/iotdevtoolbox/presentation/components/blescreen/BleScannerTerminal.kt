package com.ramasofts.iotdevtoolbox.presentation.components.blescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


val INFO = LogLabel("INFO: ", Color(0xFF0EA5E9))     // Gray
val SCAN = LogLabel("SCAN: ", Color(0xFF60A5FA))     // Blue
val FOUND = LogLabel("FOUND: ", Color(0xFF10B981))   // Green
val ERROR = LogLabel("ERROR: ", Color(0xFFF44336))   // Red
val WARN = LogLabel("WARN: ", Color(0xFFFFC107))     // Yellow
val WRITE = LogLabel("WRITE: ", Color(0xFFFFC107))     // Yellow
val NOTIFY = LogLabel("NOTIFY: ", Color(0xFFFFC107))     // Yellow

data class BleDeviceLog(
    val label: LogLabel,
    val description: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class LogLabel(
    val name: String,
    val color: Color
)

@Composable
fun BleScannerTerminal(
    logs: List<BleDeviceLog>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.lastIndex)
        }
    }

    Box(
        modifier = modifier
            .background(Color(0xFF0F172A))
            .padding(8.dp)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.height(180.dp)
        ) {
            items(logs) { log ->
                TerminalRowAligned(log)
            }
        }
    }
}


@Composable
fun TerminalRowAligned(log: BleDeviceLog) {
    val time = remember(log.timestamp) {
        SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            .format(Date(log.timestamp))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {

        // ⏱ Timestamp column (fixed width)
        Text(
            text = "[$time]",
            color = Color(0xFF475569),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.padding(end = 4.dp)
        )

        // 🏷 Label column (fixed width)
        Text(
            text = log.label.name,
            color = log.label.color,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.width(55.dp)
        )

        // 📄 Description column (flexible)
        Text(
            text = log.description,
            color = Color(0xFFCBD5E1),
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

// add preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BleScannerTerminalPreview() {
    val sampleLogs = listOf(
        BleDeviceLog(INFO, "Bluetooth adapter initialized successfully."),
        BleDeviceLog(SCAN, "Started LE scan with filter: RSSI > -100dBm"),
        BleDeviceLog(FOUND, "Device 00:11:22:33:44:55 (NORDIC_UART) found with -70"),
        BleDeviceLog(ERROR, "Failed to connect to device."),
        BleDeviceLog(WARN, "Low signal strength detected.")
    )
    BleScannerTerminal(logs = sampleLogs, modifier = Modifier.fillMaxSize())
}