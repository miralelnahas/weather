package com.golyv.data.network

import com.golyv.data.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newUrl = originalRequest.url()
            .newBuilder()
            .addQueryParameter("appid", API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}