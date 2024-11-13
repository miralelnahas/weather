package com.golyv.data.managers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConnectionManagerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    ConnectionManager {
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            ContextCompat.getSystemService(
                context,
                ConnectivityManager::class.java
            ) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}