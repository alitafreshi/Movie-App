import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}

//https://williamkingsley.medium.com/custom-gradle-plugins-in-android-23342b98e721
gradlePlugin {
    plugins {
        register("android-module-plugins") {
            id = "android-module-plugins"
            implementationClass = "com.tafreshiali.AndroidModulePlugins"
        }

        register("android-compose-module-project-config") {
            id = "android-compose-module-project-config"
            implementationClass = "com.tafreshiali.AndroidComposeModuleProjectConfig"
        }

        register("android-compose-submodule") {
            id = "android-compose-submodule"
            implementationClass = "com.tafreshiali.AndroidComposeSubModulesPlugin"
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