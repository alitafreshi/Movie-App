@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.module.plugins)
}

android {
    namespace = "com.tafreshiali.ui_kit"
}

dependencies {
    implementation(libs.androix.core.ktx)
    implementation(libs.androix.lifecycle.runtime.ktx)
    implementation(libs.androidx.annotation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)
    testImplementation(libs.google.truth)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
    testImplementation(libs.turbin)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.ktor.client.test)

    //COMPOSE
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.manifest)
    debugImplementation(libs.compose.ui.tooling)

    //COIL
    implementation(libs.compose.coil)

    //Hilt - CORE
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
kapt {
    correctErrorTypes = true
}