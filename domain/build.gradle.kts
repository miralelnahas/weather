plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "../common-configs.gradle")
android {
    namespace = "com.golyv.domain"
}

dependencies {
    implementation(project(":data"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.paging.common.ktx)

    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.test)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.espresso.core)
}