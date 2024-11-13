package com.golyv.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.golyv.domain.models.ImageDetails
import com.golyv.domain.models.WeatherDetails
import com.golyv.domain.useCases.GetImagesUseCase
import com.golyv.domain.useCases.GetLocalLocationUseCase
import com.golyv.domain.useCases.GetLocalWeatherDetailsUseCase
import com.golyv.domain.useCases.GetWeatherDetailsUseCase
import com.golyv.domain.useCases.SaveLocationUseCase
import com.golyv.domain.useCases.SaveWeatherDetailsUseCase
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val getLocalWeatherDetailsUseCase: GetLocalWeatherDetailsUseCase,
    private val saveWeatherDetailsUseCase: SaveWeatherDetailsUseCase,
    private val getLocalLocationUseCase: GetLocalLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase
) :
    ViewModel() {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private val _locationName = mutableStateOf<String?>(null)
    val locationName: State<String?> = _locationName

    private val _permissionGranted = mutableStateOf(false)
    val isPermissionGranted: State<Boolean> = _permissionGranted

    private val _isFetchingLocation = mutableStateOf(false)
    val isLoading: State<Boolean> = _isFetchingLocation

    private val _weatherDetails = mutableStateOf<WeatherDetails?>(null)
    val weatherDetails: State<WeatherDetails?> = _weatherDetails

    private val _isCelsiusSelected = mutableStateOf(true)
    val isCelsiusSelected: State<Boolean> = _isCelsiusSelected

    private val _images = mutableStateOf<List<ImageDetails>?>(null)
    val images: State<List<ImageDetails>?> = _images

    init {
        checkLocationPermission()
    }

    fun toggleTemperatureUnit() {
        _isCelsiusSelected.value = !_isCelsiusSelected.value
    }

    fun loadImages() {
        viewModelScope.launch {
            _images.value = getImagesUseCase()
        }
    }

    private fun checkLocationPermission() {
        _permissionGranted.value = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun setPermissionGranted(granted: Boolean) {
        _permissionGranted.value = granted
    }

    fun fetchLocation() {
        _isFetchingLocation.value = true
        fusedLocationClient.lastLocation.addOnSuccessListener { loc: Location? ->
            loc?.let {
                fetchLocationName(it)
                getWeatherDetails(it)
            }
        }
    }

    private fun fetchLocationName(location: Location) {
        viewModelScope.launch {
            _locationName.value = getLocalLocationUseCase()
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                _locationName.value = if (!addresses.isNullOrEmpty())
                    "${addresses[0].adminArea}, ${addresses[0].countryName}"
                else "Unknown location"

            } catch (e: Exception) {
                _locationName.value = "Unable to get location name"
            }
            saveLocationUseCase(locationName.value ?: "")
        }
    }

    private fun getWeatherDetails(location: Location) {
        viewModelScope.launch {
            _weatherDetails.value = getLocalWeatherDetailsUseCase()

            getWeatherDetailsUseCase(location.latitude, location.longitude).apply {
                onSuccess {
                    _isFetchingLocation.value = false
                    _weatherDetails.value = it
                    saveWeatherDetailsUseCase(it)
                }
            }
        }
    }
}