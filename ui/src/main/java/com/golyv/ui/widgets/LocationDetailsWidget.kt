package com.golyv.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.golyv.domain.models.WeatherDetails
import com.golyv.ui.R
import com.golyv.ui.utils.Helper.kalvinToCelsius
import com.golyv.ui.utils.Helper.kalvinToFahrenheit

@OptIn(ExperimentalGlideComposeApi::class)
@Preview
@Composable
fun LocationDetailsWidget(
    weatherDetails: WeatherDetails?,
    locationName: String?,
    isCelsiusSelected: Boolean,
    onToggleClick : () -> Unit
) {
    weatherDetails?.let {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = locationName ?: "Unknown Location",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                GlideImage(
                    model = "http://openweathermap.org/img/wn/${it.weatherIcon}@2x.png",
                    contentDescription = "Weather icon",
                    modifier = Modifier.size(64.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (isCelsiusSelected) {
                            "${kalvinToCelsius(it.temp)}°C"
                        } else {
                            "${kalvinToFahrenheit(it.temp)}°F"
                        },
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                TemperatureUnitToggleWidget(
                    isCelsiusSelected = isCelsiusSelected,
                    onToggleClick = onToggleClick
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_humidity),
                            contentDescription = "Humidity",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${it.humidity}%",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_wind_speed),
                            contentDescription = "Wind Speed",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${it.windSpeed} km/h",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    } ?: Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No Weather Data available.",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}