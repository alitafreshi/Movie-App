import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
repositories {
    google()
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
    maven(url = "https://jitpack.io")
}

plugins {
    `kotlin-dsl`
}

//https://williamkingsley.medium.com/custom-gradle-plugins-in-android-23342b98e721
gradlePlugin {
    plugins {
        register("android-module-plugins") {
            id = "android-module-plugins"
            implementationClass = "com.tafreshiali.AndroidModulePlugins"
        }

        register("android-module-project-config") {
            id = "android-module-project-config"
            implementationClass = "com.tafreshiali.AndroidModuleProjectConfig"
        }
    }
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
    //https://github.com/google/dagger/issues/3068#issuecomment-1595158810
    implementation(libs.hilt.gradle.plugin)
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}