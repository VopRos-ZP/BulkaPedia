buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("com.android.tools.build:gradle:8.0.2")
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinKapt) apply false
    alias(libs.plugins.hiltAndroid) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.kotlinSafeArgs) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
