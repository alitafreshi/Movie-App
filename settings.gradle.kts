pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven(url = "https://jitpack.io")
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://jitpack.io")
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "MovieApp"
include(":app")
include(":core")
include(":core:network")
include(":core:coroutines-dispatchers")
include(":core:data-state")
include(":core:firebase")
include(":core:app-state-manager")
include(":core:components")
include(":home")
include(":home:data")
include(":home:domain")
include(":home:presentation")
include(":ui-kit")
