@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.kotlin.jvm.get().pluginId)
    id(libs.plugins.org.jetbrains.kotlin.kapt.get().pluginId)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlin.coroutines)
    //hilt core
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
kapt {
    correctErrorTypes = true
}