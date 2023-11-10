@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.tafreshiali.common")
}

android {
    namespace = NameSpaces.homePresentation
}

dependencies {

    implementation(libs.androix.core.ktx)
    implementation(libs.androix.lifecycle.runtime.ktx)
    implementation(libs.androidx.annotation)

    testImplementation(Junit.junit)
    androidTestImplementation(Junit.junitTestExt)
    androidTestImplementation(Junit.junitTestExtKtx)
    androidTestImplementation(Espresso.espresso)

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
    implementation(project(Modules.homeDomain))

    //HOME DATA MODULE
    implementation(project(Modules.homeData))

    //CORE APP STATE MODULE
    implementation(project(Modules.appStatemanger))

    //CORE COMPONENTS MODULE
    implementation(project(Modules.components))

    //CORE DATA STATE MODULE
    implementation(project(Modules.dataState))

}
kapt {
    correctErrorTypes = true
}