plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

apply(from = "../common-configs.gradle")
android {
    namespace = "com.golyv.data"

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.datastore.preferences)
    implementation(libs.datastore.preferences.core)


    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    implementation(libs.paging.common.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    annotationProcessor(libs.room.compiler)

    kapt(libs.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.test)
    androidTestImplementation(libs.espresso.core)
}

kapt {
    correctErrorTypes = true
}
