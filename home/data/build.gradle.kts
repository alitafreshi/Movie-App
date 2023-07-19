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

    implementation(AndroidX.androidxAnnotation)
    implementation(Hilt.javaInject)

    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)

    //ktor client
    implementation(Ktor.ktor_client_core)
    implementation(Ktor.ktor_client_gson)

    //domain module
    implementation(project(Modules.homeDomain))

    //network module
    implementation(project(Modules.network))

}