plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.vopros.bulkapedia.gears.domain"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":gears:data"))
    /** Firebase **/
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
}