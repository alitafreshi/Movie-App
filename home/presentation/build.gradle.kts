plugins {
    id(Plugins.androidLibrary)
    kotlin(KotlinPlugins.android)
    kotlin(KotlinPlugins.kapt)
    id(Plugins.safeArgsNavigationPlugin)
    id(Plugins.hilt)
    id(KotlinPlugins.parcelize)
}

android {
    namespace = NameSpaces.homePresentation
    compileSdk = Application.compileSdk

    defaultConfig {
        minSdk = Application.minSdk
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
        kotlinCompilerExtensionVersion = Compose.compose_compiler_version
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.androidxAnnotation)
    implementation(AndroidX.appCompatActivity)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.fragmentKtx)
    testImplementation(Junit.junit)
    androidTestImplementation(Junit.junitTestExt)
    androidTestImplementation(Junit.junitTestExtKtx)
    androidTestImplementation(Espresso.espresso)

    //COMPOSE
    implementation(platform(Compose.composeBoom))
    androidTestImplementation(Compose.composeBoom)

    implementation(Compose.compose_activity)
    implementation(Compose.compose_material_3)
    implementation(Compose.compose_preview)
    implementation(Compose.compose_ui_tooling)
    implementation(Compose.compose_ui_graphics)
    implementation(Compose.compose_compiler)
    implementation(Compose.compose_constraint_layout)
    implementation(Compose.compose_viewModel)
    implementation(Compose.compose_view_binding)
    debugImplementation(Compose.compose_ui_manifest)
    debugImplementation(Compose.compose_ui_tooling)

    //COIL
    implementation(Coil.coil)

    //Hilt - CORE
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)

    //Hilt - NAVIGATION
    implementation(Hilt.hiltNavigation)
    implementation(Hilt.hiltFragmentsNavigation)

    //JETPACK-NAVIGATION
    implementation(Navigation.navigation_fragments)
    implementation(Navigation.navigation_kotlin_ui)

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