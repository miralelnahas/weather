package com.golyv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.golyv.domain.models.ImageDetails
import com.golyv.ui.utils.Helper

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
@Preview
fun ImageItemWidget(
    image: ImageDetails,
    isCelsiusSelected: Boolean,
    onNavigateToLocationDetails: (Long, Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onNavigateToLocationDetails(image.id, isCelsiusSelected) }
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = image.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = image.locationName,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = if (isCelsiusSelected) {
                        "${Helper.kalvinToCelsius(image.weatherDetails.temp)}°C"
                    } else {
                        "${Helper.kalvinToFahrenheit(image.weatherDetails.temp)}°F"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )


                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${image.weatherDetails.humidity}%",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "${image.weatherDetails.windSpeed} km/h",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}