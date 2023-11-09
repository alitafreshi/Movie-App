@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.com.android.library.get().pluginId)
    id(libs.plugins.org.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.org.jetbrains.kotlin.kapt.get().pluginId)
    id(libs.plugins.jetpack.navigation.safe.args.plugin.get().pluginId)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
    id(libs.plugins.org.jetbrains.koltin.parcelize.plugin.get().pluginId)
}

android {
    namespace = NameSpaces.homePresentation
    compileSdk = libs.versions.apllication.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.application.minsdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androix.core.ktx)
    implementation(libs.androix.lifecycle.runtime.ktx)
    implementation(libs.androidx.annotation)

    testImplementation(Junit.junit)
    androidTestImplementation(Junit.junitTestExt)
    androidTestImplementation(Junit.junitTestExtKtx)
    androidTestImplementation(Espresso.espresso)

    //COMPOSE
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.manifest)
    debugImplementation(libs.compose.ui.tooling)

    //COIL
    implementation(libs.compose.coil)

    //Hilt - CORE
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Hilt - NAVIGATION
    implementation(libs.bundles.hilt.navigation)


    //JETPACK-NAVIGATION
    implementation(libs.bundles.jetpack.navigation)

    //HOME DOMAIN MODULE
    implementation(project(Modules.homeDomain))

    //HOME DATA MODULE
    implementation(project(Modules.homeData))

    //CORE APP STATE MODULE
    implementation(project(Modules.appStatemanger))

    //CORE COMPONENTS MODULE
    implementation(project(Modules.components))

    //CORE DATA STATE MODULE
    implementation(project(Modules.dataState))

}
kapt {
    correctErrorTypes = true
}