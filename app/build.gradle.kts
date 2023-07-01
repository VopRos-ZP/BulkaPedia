@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinSafeArgs)
    alias(libs.plugins.kotlinKapt)
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.bulkapedia"
        minSdk = 26
        targetSdk = 33
        versionCode = 36
        versionName = "0.3.6"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.bulkapedia"
}

dependencies {
    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)
    debugImplementation(libs.androidx.ui.tooling)
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Json
    implementation(libs.kotlinx.serialization.json)
    // AndroidX
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.navigation.runtime.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.datastore.preferences)
    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.auth) {
        exclude(module = "play-services-safetynet")
    }
    // DI
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)// replace to ksp
    // In-App-Update
    implementation(libs.app.update.ktx)
    // YouTube
    implementation(libs.core)
    // Glide
    implementation(libs.glide)
    implementation(libs.compose)
}