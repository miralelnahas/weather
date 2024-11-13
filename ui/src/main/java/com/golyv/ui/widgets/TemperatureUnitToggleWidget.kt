package com.golyv.ui.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.golyv.ui.R

@Composable
@Preview
fun TemperatureUnitToggleWidget(isCelsiusSelected: Boolean, onToggleClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "C",
            style = MaterialTheme.typography.titleLarge
        )

        IconButton(onClick = onToggleClick) {
            Icon(
                painter = painterResource(id = if (!isCelsiusSelected) R.drawable.ic_toggle_on else R.drawable.ic_toggle_off),
                contentDescription = "Toggle Temperature Unit",
                Modifier.size(30.dp)
            )
        }


        Text(
            text = "F",
            style = MaterialTheme.typography.titleLarge
        )
    }

}