@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.google.services)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.tafreshiali.firebase"
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
}

dependencies {

    implementation(libs.androix.core.ktx)
    implementation(libs.androix.lifecycle.runtime.ktx)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.lifecycle.services)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)

    //FCM
    api(platform(libs.firebase.bom))
    api(libs.firebase.cloud.messaging.ktx)
    // api(libs.firebase.crashlytics.ktx)
    //api(libs.firebase.analytics.ktx)

    //glide
    api(libs.glide)
    kapt(libs.glide.compiler)

    //Hilt - CORE
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
kapt {
    correctErrorTypes = true
}