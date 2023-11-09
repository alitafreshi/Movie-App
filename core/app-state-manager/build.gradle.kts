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
    //kotlinCoroutines
    implementation(libs.kotlin.coroutines)

    //javax
    implementation(libs.java.inject)

    //coroutines dispatchers
    implementation(project(Modules.coroutinesDispatchers))
}