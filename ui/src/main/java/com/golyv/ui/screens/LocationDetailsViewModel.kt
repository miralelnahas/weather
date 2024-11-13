package com.golyv.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.golyv.domain.models.ImageDetails
import com.golyv.domain.useCases.GetImageDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(private val getImageDetailsUseCase: GetImageDetailsUseCase) :
    ViewModel() {
    private val _imageDetails = mutableStateOf<ImageDetails?>(null)
    val imageDetails: State<ImageDetails?> = _imageDetails

    fun getImageDetails(id: Long) = viewModelScope.launch {
        _imageDetails.value = getImageDetailsUseCase(id)
    }
}