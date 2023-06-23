pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BulkaPedia"
include(":app")
