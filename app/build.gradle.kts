@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.com.android.application.get().pluginId)
    id(libs.plugins.org.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.org.jetbrains.kotlin.kapt.get().pluginId)
    id(libs.plugins.org.jetbrains.kotlin.serialization.plugin.get().pluginId)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
    id(libs.plugins.jetpack.navigation.safe.args.plugin.get().pluginId)
    id(libs.plugins.org.jetbrains.koltin.parcelize.plugin.get().pluginId)
}

android {
    namespace = Application.appId
    compileSdk = libs.versions.apllication.compileSdk.get().toInt()

    defaultConfig {
        applicationId = Application.appId
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
}

dependencies {
    //Androidx
    implementation(libs.androix.core.ktx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androix.lifecycle.runtime.ktx)
    implementation(libs.androix.livedata.ktx)
    implementation(libs.androix.appcompat)
    implementation(libs.androix.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
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
    implementation(project(Modules.firebase))

    //COMPONENTS MODULE
    implementation(project(Modules.components))

    //HOME MODULE
    implementation(project(Modules.homePresentation))

}
kapt {
    correctErrorTypes = true
}