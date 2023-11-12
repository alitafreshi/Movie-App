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

    implementation(libs.androidx.annotation)
    implementation(libs.java.inject)

    //kotlinCoroutines
    implementation(libs.kotlin.coroutines)

    //ktor client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.serialization.gson)

    //domain module
    implementation(projects.home.domain)

    //network module
    implementation(projects.core.network)

}