plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
    kotlin(KotlinPlugins.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)

    //ktor client
    implementation(Ktor.ktor_client_core)
    implementation(Ktor.ktor_client_okHttp)
    implementation(Ktor.ktor_client_logging)
    implementation(Ktor.ktor_client_gson)
    implementation(Ktor.ktor_client_content_negotiation)
    implementation(Ktor.ktor_client_custom_logging)

    //hilt core
    implementation(Hilt.hiltCore)
    kapt(Hilt.hiltCompiler)
}
kapt {
    correctErrorTypes = true
}