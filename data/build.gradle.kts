plugins {
    id(androidLib)
    id(Hilt.plugin)
    kotlin(androidPlugin)
    kotlin(serialization)
    kotlin(kaptPlugin)
}

android {
    namespace = "com.vopros.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    with(Firebase) {
        implementation(databaseKtx)
        implementation(firestoreKtx)
        implementation(authKtx)
        implementation(core)
    }
    with(Hilt) {
        implementation(android)
        kapt(compiler)
    }
    implementation(Deps.kotlin_serialization)
}