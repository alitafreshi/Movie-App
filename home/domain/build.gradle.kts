@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
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
    implementation(project(":core:data-state"))
}