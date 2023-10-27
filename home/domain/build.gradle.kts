@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.java.library.plugin)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.ktor.serialization.gson)
    implementation(libs.androidx.annotation)
    implementation(libs.java.inject)

    //DATA STATE MODULE
    implementation(project(Modules.dataState))
}