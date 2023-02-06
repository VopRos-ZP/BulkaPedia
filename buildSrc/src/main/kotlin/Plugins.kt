const val androidPlugin = "android"
const val androidApp = "com.android.application"
const val androidLib = "com.android.library"
const val multiplatform = "multiplatform"
const val composePlugin = "org.jetbrains.compose"
const val cocoapods = "native.cocoapods"
const val serialization = "plugin.serialization"

object Versions {
    const val kotlin = "1.7.20"
    const val kotlin_gradle_plugin = "1.7.20"
    const val android_gradle_plugin = "7.3.1"
    const val compose_version= "1.2.2"
    const val composeCompileVersion= "1.3.2"
    const val coroutines = "1.6.4"
    const val material = "1.4.0"
    const val kotlinxDateTime = "0.4.0"
    const val activity_compose = "1.6.1"
    const val frameworkName = "shared"
    const val viewModelCompose = "2.5.1"
}

object Deps {
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_gradle_plugin}"
    const val kotlin_serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val fragment = "androidx.fragment:fragment-ktx:1.5.5"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val datastorePreferences = "androidx.datastore:datastore-preferences:1.0.0"

    const val pager = "com.google.accompanist:accompanist-pager:0.28.0"
    const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:0.28.0"
}

object Compose {
    const val activity = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val runtime = "androidx.compose.runtime:runtime:1.3.2"
    const val ui = "androidx.compose.ui:ui:1.3.2"
    const val uiUtil = "androidx.compose.ui:ui-util:1.3.3"
    const val uiTool = "androidx.compose.ui:ui-tooling:1.3.2"
    const val uiPreview = "androidx.compose.ui:ui-tooling-preview:1.3.2"
    const val material = "androidx.compose.material:material:1.3.1"
    const val materialIcons = "androidx.compose.material:material-icons-extended:1.3.1"
    const val foundation = "androidx.compose.foundation:foundation:1.3.1"
    const val compiler = "androidx.compose.compiler:compiler:${Versions.composeCompileVersion}"
    const val navigation = "androidx.navigation:navigation-compose:2.5.3"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    const val livedata = "androidx.compose.runtime:runtime-livedata:1.3.2"
}

object Firebase {
    // other
    const val database = "com.google.firebase:firebase-database:20.1.0"
    const val databaseKtx = "com.google.firebase:firebase-database-ktx:20.1.0"

    const val firestore = "com.google.firebase:firebase-firestore:24.4.1"
    const val firestoreKtx = "com.google.firebase:firebase-firestore-ktx:24.4.1"

    const val auth = "com.google.firebase:firebase-auth:21.1.0"
    const val authKtx = "com.google.firebase:firebase-auth-ktx:21.1.0"

    const val storage = "com.google.firebase:firebase-storage:20.1.0"
    const val storageKtx = "com.google.firebase:firebase-storage-ktx:20.1.0"

    const val core = "com.google.firebase:firebase-core:21.1.1"

    // in multiplatform
//    private const val version = "1.6.2"
//    const val database = "dev.gitlive:firebase-database:$version"
//    const val firestore = "dev.gitlive:firebase-firestore:$version"
//    const val auth = "dev.gitlive:firebase-auth:$version"
//    const val storage = "dev.gitlive:firebase-database:$version"
//    const val core = "com.google.firebase:firebase-core:21.1.1"
}

object Glide {
    private const val version = "4.14.2"
    const val glide = "com.github.bumptech.glide:glide:$version"
    const val compiler = "com.github.bumptech.glide:compiler:$version"
    const val compose = "com.github.bumptech.glide:compose:1.0.0-alpha.1"
}

object InAppUpdate {
    private const val version = "2.0.1"
    const val update = "com.google.android.play:app-update:$version"
    const val updateKtx = "com.google.android.play:app-update-ktx:$version"
}

object Hilt {
    private const val version = "2.44"
    const val plugin = "com.google.dagger.hilt.android"
    const val android = "com.google.dagger:hilt-android:$version"
    const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val compiler = "com.google.dagger:hilt-compiler:$version"
}
