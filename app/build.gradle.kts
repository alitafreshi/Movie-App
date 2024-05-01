@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.org.jetbrains.kotlin.serialization.plugin)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.jetpack.navigation.safe.args.plugin)
    alias(libs.plugins.org.jetbrains.koltin.parcelize.plugin)
}

android {
    namespace = "com.tafreshiali.moviewapp"
    compileSdk = libs.versions.apllication.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.tafreshiali.moviewapp"
        minSdk = libs.versions.application.minsdk.get().toInt()
        multiDexEnabled = true
        targetSdk = libs.versions.apllication.targetsdk.get().toInt()
        versionCode = libs.versions.appication.version.code.get().toInt()
        versionName = libs.versions.application.version.name.toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "default"
    productFlavors {
        create("qa") {
            dimension = "default"
            applicationIdSuffix = ".qa"
        }
    }
}

//Add dependency for a product flavor
val qaImplementation by configurations

dependencies {
    //Androidx
    implementation(libs.androix.core.ktx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androix.lifecycle.runtime.ktx)
    implementation(libs.androix.livedata.ktx)
    implementation(libs.androix.appcompat)
    implementation(libs.androix.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)
    testImplementation(libs.google.truth)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)


    //COMPOSE
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.manifest)
    debugImplementation(libs.compose.ui.tooling)

    //LEAK CANARY
    debugImplementation(libs.leakcanary)

    //Hilt - CORE
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Hilt - NAVIGATION
    implementation(libs.bundles.hilt.navigation)

    //JETPACK-NAVIGATION
    implementation(libs.bundles.jetpack.navigation)

    //FIREBASE MODULE
    implementation(projects.core.firebase)

    //COMPONENTS MODULE
    implementation(projects.core.components)

    //HOME MODULE
    implementation(projects.home.presentation)
    qaImplementation(libs.java.inject)
}
kapt {
    correctErrorTypes = true
}