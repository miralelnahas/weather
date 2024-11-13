package com.golyv.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.golyv.ui.widgets.CameraWidget
import com.golyv.ui.widgets.ImageItemWidget
import com.golyv.ui.widgets.LocationDetailsWidget

@Composable
@Preview
fun LocationScreen(onNavigateToLocationDetails: (Long, Boolean) -> Unit) {
    val vm: LocationViewModel = hiltViewModel()

    val isPermissionGranted by vm.isPermissionGranted
    val isLoading by vm.isLoading
    val weatherDetails by vm.weatherDetails
    val locationName by vm.locationName
    val isCelsiusSelected by vm.isCelsiusSelected
    val images by vm.images

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        vm.setPermissionGranted(isGranted)
    }

    LaunchedEffect(Unit) {
        vm.loadImages()
    }

    LaunchedEffect(isPermissionGranted) {
        if (isPermissionGranted) {
            vm.fetchLocation()
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    Scaffold(floatingActionButton = {
        CameraWidget(weatherDetails, locationName, isCelsiusSelected, onNavigateToLocationDetails)
    }, content = {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it.calculateBottomPadding())
        ) {
            if (isLoading && weatherDetails == null) {
                Text(
                    "Loading...",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            } else {
                LocationDetailsWidget(weatherDetails, locationName, isCelsiusSelected) {
                    vm.toggleTemperatureUnit()
                }
            }

            if (images.isNullOrEmpty())
                Text(
                    "There are no images yet.",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            else
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(images!!.size) { index ->
                        val image = images!![index]
                        ImageItemWidget(image, isCelsiusSelected, onNavigateToLocationDetails)
                    }
                }
        }
    })
}