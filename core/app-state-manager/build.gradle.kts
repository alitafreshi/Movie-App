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

    //javax
    implementation(libs.java.inject)

    //coroutines dispatchers
    implementation(projects.core.coroutinesDispatchers)
}