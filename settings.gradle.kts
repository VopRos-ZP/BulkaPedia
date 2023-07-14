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
include(":firebase:firestore")
include(":firebase:database")
include(":core:domain")
include(":core:ui")
/* UserSet */
include(":sets:data")
include(":sets:domain")
include(":sets:ui")
/* Effects */
include(":effects:data")
include(":effects:domain")
include(":effects:ui")
/* Users */
include(":users:data")
include(":users:domain")
include(":users:ui")
/* Comments */
include(":comments:data")
include(":comments:domain")
include(":comments:ui")
/* Categories */
include(":categories:data")
include(":categories:domain")
include(":categories:ui")
/* DevChat */
include(":chat:data")
include(":chat:domain")
include(":chat:ui")
/* Gears */
include(":gears:data")
include(":gears:domain")
include(":gears:ui")
/* HeroInfo */
include(":heroInfo:data")
include(":heroInfo:domain")
include(":heroInfo:ui")
/* Heroes */
include(":heroes:data")
include(":heroes:domain")
include(":heroes:ui")
/* Mains */
include(":mains:data")
include(":mains:domain")
include(":mains:ui")
/* Maps */
include(":maps:data")
include(":maps:domain")
include(":maps:ui")
/* Mechanics */
include(":mechanics:data")
include(":mechanics:domain")
include(":mechanics:ui")