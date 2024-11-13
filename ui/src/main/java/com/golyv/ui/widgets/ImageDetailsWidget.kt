package com.golyv.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.golyv.domain.models.WeatherDetails
import com.golyv.ui.R
import com.golyv.ui.utils.Helper

@Composable
fun ImageDetailsWidget(
    weatherDetails: WeatherDetails,
    locationName: String,
    isCelsiusSelected: Boolean
) {
    Column {
        // Location Name
        Text(
            text = locationName,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Weather details
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Temperature
            Text(
                text = if (isCelsiusSelected) {
                    "${Helper.kalvinToCelsius(weatherDetails.temp)}°C"
                } else {
                    "${Helper.kalvinToFahrenheit(weatherDetails.temp)}°F"
                },
                style = MaterialTheme.typography.headlineSmall
            )

            // Humidity
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_humidity),
                    contentDescription = "Humidity Icon"
                )
                Text("${weatherDetails.humidity}%", style = MaterialTheme.typography.bodyLarge)
            }

            // Wind Speed
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_wind_speed),
                    contentDescription = "Wind Speed Icon"
                )
                Text("${weatherDetails.windSpeed} km/h", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}