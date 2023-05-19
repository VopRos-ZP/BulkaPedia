plugins {
    id(androidApp)
    kotlin(androidPlugin)
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
    id(Hilt.plugin)
    kotlin("kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.bulkapedia"
        minSdk = 26
        targetSdk = 33
        versionCode = 35
        versionName = "0.3.5"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.bulkapedia"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    with (Compose) {
        implementation(activity)
        implementation(ui)
        implementation(uiPreview)
        implementation(foundation)
        implementation(material)
        implementation(materialIcons)
        implementation(compiler)
        implementation(navigation)
        implementation(viewModel)
        implementation(livedata)
        implementation(constraintLayout)
        debugImplementation(uiTool)
    }
    with(Deps) {
        implementation(material)
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
        implementation(datastorePreferences)
        implementation(fragment)
        implementation(kotlin_serialization)
    }
    with (Firebase) {
        implementation(database)
        implementation(databaseKtx)
        implementation(firestore)
        implementation(firestoreKtx)
        implementation(auth) {
            exclude(module = "play-services-safetynet")
        }
        implementation(authKtx)
        implementation(storage)
        implementation(storageKtx)
        implementation(core)
    }
    with(Glide) {
        implementation(glide)
        implementation(compose)
        kapt(compiler)
    }
    with(Hilt) {
        implementation(android)
        implementation(navigation)
        kapt(compiler)
    }
    with(InAppUpdate) {
        implementation(updateKtx)
    }
    // androidx base
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    // YouTube
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.0.0")
}

kapt {
    correctErrorTypes = true
}