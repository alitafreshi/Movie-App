plugins {
    id(Plugins.androidLibrary)
    kotlin(KotlinPlugins.android)
    id(Plugins.googleGmsPlugin)
    id(Plugins.hilt)
    kotlin(KotlinPlugins.kapt)

}

android {
    namespace = NameSpaces.coreFriebase
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
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.androidxAnnotation)
    implementation("androidx.lifecycle:lifecycle-service:2.6.1")
    testImplementation(Junit.junit)
    androidTestImplementation(Junit.junitTestExt)
    androidTestImplementation(Junit.junitTestExtKtx)
    androidTestImplementation(Espresso.espresso)
    //FCM
    api(Firbase.cloud_messaging)

    //Hilt - CORE
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)
}
kapt {
    correctErrorTypes = true
}