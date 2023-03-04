plugins {
    id(androidApp)
    kotlin(androidPlugin)
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id(Hilt.plugin)
    kotlin("kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.bulkapedia"
        minSdk = 26
        targetSdk = 33
        versionCode = 34
        versionName = "0.3.4"
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
        kotlinCompilerExtensionVersion = "1.3.2"
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
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    // compose
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
    implementation(Deps.material)
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.datastorePreferences)
    implementation(Deps.fragment)

    implementation(Deps.pager)
    implementation(Deps.pagerIndicators)

    // androidx base
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    // firebase
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
        annotationProcessor(compiler)
    }
    with(Hilt) {
        implementation(android)
        implementation(navigation)
        kapt(compiler)
    }
    with(InAppUpdate) {
        implementation(updateKtx)
    }
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")
}

kapt {
    correctErrorTypes = true
}