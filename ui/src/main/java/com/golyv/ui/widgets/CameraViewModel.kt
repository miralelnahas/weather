package com.golyv.ui.widgets

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.golyv.domain.models.WeatherDetails
import com.golyv.domain.useCases.SaveImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val saveImageUseCase: SaveImageUseCase) :
    ViewModel() {
    private val _id = mutableStateOf<Long?>(null)
    val id: State<Long?> = _id

    fun saveImage(capturedImageUri: String, locationName: String, weatherDetails: WeatherDetails) {
        viewModelScope.launch {
            _id.value = saveImageUseCase(capturedImageUri, locationName, weatherDetails)
        }
    }

    fun clearId() {
        _id.value = null
    }

}