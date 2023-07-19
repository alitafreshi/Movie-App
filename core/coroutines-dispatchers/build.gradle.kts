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
    implementation(Kotlin.kotlinCoroutines)
    //hilt core
    implementation(Hilt.hiltCore)
    kapt(Hilt.hiltCompiler)
}
kapt {
    correctErrorTypes = true
}