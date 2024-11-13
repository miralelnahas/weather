plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.8.0"
}

apply(from = "../common-configs.gradle")
android {
    namespace = "com.golyv.ui"
    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}
dependencies {
    implementation(project(":domain"))

    implementation(libs.activity.compose)
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.animation)
    implementation(libs.play.services.location)
    implementation(libs.ui.text.google.fonts)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation(libs.compose.ui.tooling)
//    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.fragment.ktx)

    implementation("com.squareup.okhttp3:okhttp:3.14.9")
    implementation(libs.serializable)

    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)

    implementation(libs.paging.common.ktx)
    implementation(libs.paging.runtime.ktx)

    implementation(libs.glide)
    implementation(libs.glide.okhttp.integration)

    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.extensions)
    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.core.splashscreen)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.test)
    androidTestImplementation(libs.espresso.core)
}

kapt {
    correctErrorTypes = true
}