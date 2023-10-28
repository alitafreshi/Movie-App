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
    //kotlinCoroutines
    implementation(libs.kotlin.coroutines)

    //ktor client
    implementation(libs.bundles.ktor)

    //hilt core
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
kapt {
    correctErrorTypes = true
}