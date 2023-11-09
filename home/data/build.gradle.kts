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

    implementation(libs.androidx.annotation)
    implementation(libs.java.inject)

    //kotlinCoroutines
    implementation(libs.kotlin.coroutines)

    //ktor client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.serialization.gson)

    //domain module
    implementation(project(Modules.homeDomain))

    //network module
    implementation(project(Modules.network))

}