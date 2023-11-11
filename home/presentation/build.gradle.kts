@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.module.plugins)
    alias(libs.plugins.android.module.project.config)
}

android {
    namespace = "com.tafreshiali.presentation"
}

dependencies {

    implementation(libs.androix.core.ktx)
    implementation(libs.androix.lifecycle.runtime.ktx)
    implementation(libs.androidx.annotation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)

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

    //Hilt - NAVIGATION
    implementation(libs.bundles.hilt.navigation)


    //JETPACK-NAVIGATION
    implementation(libs.bundles.jetpack.navigation)

    //HOME DOMAIN MODULE
    implementation(project(":home:domain"))

    //HOME DATA MODULE
    implementation(project(":home:data"))

    //CORE APP STATE MODULE
    implementation(project(":core:app-state-manager"))

    //CORE COMPONENTS MODULE
    implementation(project(":core:components"))

    //CORE DATA STATE MODULE
    implementation(project(":core:data-state"))

}
kapt {
    correctErrorTypes = true
}