plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.vopros.bulkapedia.maps.ui"
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
    implementation(project(":core:ui"))
    implementation(project(":maps:data"))
    implementation(project(":maps:domain"))
    /** Compose **/
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.constraintlayout.compose)
    debugImplementation(libs.androidx.ui.tooling)
    /** AndroidX **/
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    /** Glide **/
    implementation(libs.glide)
    implementation(libs.compose)
    /** DI **/
    implementation(libs.hilt.android)
    implementation(libs.androidx.navigation.compose)
    ksp(libs.hilt.compiler)
    /** Firebase **/
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
}