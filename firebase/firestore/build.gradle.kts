plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.vopros.bulkapedia.firebase.firestore"
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
    /** Project **/
    implementation(project(":core:domain"))
    implementation(project(":categories:data"))
    implementation(project(":comments:data"))
    implementation(project(":chat:data"))
    implementation(project(":gears:data"))
    implementation(project(":heroes:data"))
    implementation(project(":heroInfo:data"))
    implementation(project(":effects:data"))
    implementation(project(":mains:data"))
    implementation(project(":maps:data"))
    implementation(project(":mechanics:data"))
    implementation(project(":sets:data"))
    implementation(project(":users:data"))
    /** Firebase **/
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.auth) {
        exclude(module = "play-services-safetynet")
    }
}