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
    implementation(Ktor.ktor_client_gson)
    implementation(AndroidX.androidxAnnotation)
    implementation(Hilt.javaInject)

    //DATA STATE MODULE
    implementation(project(Modules.dataState))
}