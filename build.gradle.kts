// Top-level build file where you can add configuration options common to all subprojects/modules.
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.build.gradle)
        classpath(libs.kotlin.gradle)
        classpath(libs.google.services)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }

}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.hiltAndroid) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
true