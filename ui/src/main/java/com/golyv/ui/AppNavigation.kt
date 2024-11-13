package com.golyv.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.golyv.ui.screens.LocationDetailsScreen
import com.golyv.ui.screens.LocationScreen
import kotlinx.serialization.Serializable

@Serializable
object Location

@Serializable
data class LocationDetails(val id: Long, val isCelsiusSelected: Boolean)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Location) {
        composable<Location> {
            LocationScreen { id, isCelciusSelected ->
                navController.navigate(
                    route = LocationDetails(
                        id = id,
                        isCelsiusSelected = isCelciusSelected
                    )
                )
            }
        }
        composable<LocationDetails> {
            val locationDetails: LocationDetails = it.toRoute()
            LocationDetailsScreen(locationDetails.id, locationDetails.isCelsiusSelected) {
                navController.popBackStack()
            }
        }
    }
}