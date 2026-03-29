package com.ramasofts.iotdevtoolbox.presentation.components.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramasofts.iotdevtoolbox.R

@Composable
fun HomeToolCard(
    icon: Int = R.drawable.ic_ble,
    title: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Card(
            onClick = onClick,
            modifier = Modifier,
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            border = BorderStroke(.2.dp, Color(0xFFE2E8F0)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier
                    .padding( 30.dp)
                    .size(30.dp),
                tint = Color.Blue
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BleCardPreview() {
    HomeToolCard(
        icon = R.drawable.ic_ble,
        title = "BLE",
        onClick = {}
    )
}