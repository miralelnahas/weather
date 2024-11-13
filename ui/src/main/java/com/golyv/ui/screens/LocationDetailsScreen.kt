package com.golyv.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.golyv.ui.widgets.ImageDetailsWidget

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailsScreen(id: Long, isCelsiusSelected: Boolean, onBackPressed : () -> Unit) {
    val vm: LocationDetailsViewModel = hiltViewModel()
    val details by vm.imageDetails

    LaunchedEffect(id) {
        vm.getImageDetails(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Image Details") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        details?.let {
            Column(modifier = Modifier.padding(8.dp).padding(padding)) {
                ImageDetailsWidget(
                    weatherDetails = it.weatherDetails,
                    locationName = it.locationName,
                    isCelsiusSelected = isCelsiusSelected
                )
                Spacer(modifier = Modifier.height(16.dp))

                GlideImage(
                    model = it.imageUrl,
                    contentDescription = "Location Image",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        } ?: Text("No Data")
    }
}
