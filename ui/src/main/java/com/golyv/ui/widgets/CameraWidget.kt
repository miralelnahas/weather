package com.golyv.ui.widgets

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.golyv.domain.models.WeatherDetails
import com.golyv.ui.R
import com.golyv.ui.utils.createImageFile
import java.util.Objects

@Composable
@Preview
fun CameraWidget(
    weatherDetails: WeatherDetails?,
    locationName: String?,
    isCelciusSelected: Boolean,
    onNavigateToLocationDetails: (Long, Boolean) -> Unit
) {
    val vm: CameraViewModel = hiltViewModel()
    val id by rememberSaveable { vm.id }
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context), "com.golyv.weather" + ".provider", file
    )
    var capturedImageUri by rememberSaveable {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        capturedImageUri = if (it) uri else Uri.EMPTY
    }

    fun launchCamera() {
        if (weatherDetails != null) cameraLauncher.launch(uri)
        else Toast.makeText(
            context, "Please wait weather details to finish loading", Toast.LENGTH_SHORT
        ).show()
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            launchCamera()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    FloatingActionButton(onClick = {
        val permissionCheckResult =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            launchCamera()
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }) {
        Image(
            painterResource(R.drawable.ic_capture),
            contentDescription = "",
            modifier = Modifier.size(28.dp)
        )
    }

    LaunchedEffect(capturedImageUri) {
        if (capturedImageUri.path?.isNotEmpty() == true && weatherDetails != null) {
            vm.saveImage(
                capturedImageUri.toString(), locationName ?: "", weatherDetails
            )
            capturedImageUri = Uri.EMPTY
        }
    }

    LaunchedEffect(id) {
        id?.let {
            onNavigateToLocationDetails(it, isCelciusSelected)
            vm.clearId()
        }
    }
}