plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)

    //javax
    implementation(Hilt.javaInject)

    //coroutines dispatchers
    implementation(project(Modules.coroutinesDispatchers))
}