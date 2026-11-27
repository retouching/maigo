pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "maigo"

include(":app")

include(":core:model")
include(":core:common")
include(":core:security")
include(":core:database")
include(":core:network")
include(":core:media")
include(":core:data")
include(":core:designsystem")
include(":core:testing")

include(":feature:home")
include(":feature:search")
include(":feature:library")
include(":feature:player")
include(":feature:history")
include(":feature:radio")
include(":feature:settings")
