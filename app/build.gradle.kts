plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    //alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
    id("kotlin-parcelize")
}

android {
    namespace = "ru.bulkapedia"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.bulkapedia"
        minSdk = 28
        versionCode = 2
        versionName = "0.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        jvmTarget = "${JavaVersion.VERSION_17}"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    /** Firebase **/
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    /** Compose **/
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    /** DI **/
    implementation(libs.bundles.di)
    ksp(libs.hilt.compiler)
    /** Navigation **/
    implementation(libs.navigation.compose)
    /** Core **/
    implementation(libs.bundles.core)
    /** Coroutines **/
    implementation(libs.kotlin.coroutines.android)
    /** Coil **/
    implementation(libs.coil.compose)
    /** DataStore **/
    implementation(libs.datastore.preferences)
    /** Google **/
    implementation(libs.integrity)
    implementation(libs.app.update.ktx)
    implementation(libs.gson)
    /** Decompose **/
    implementation(libs.bundles.decompose)
    /** MVI **/
    implementation(libs.bundles.mvikotlin)
    /** Test **/
    testImplementation(libs.kotlin.test)
}
