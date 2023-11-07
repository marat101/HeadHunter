pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HeadHunter"
include(":app")

include(":core_network")
include(":core_network:api")
include(":core_network:impl")

include(":core_navigation")

include(":feature_home")
include(":feature_home:api")
include(":feature_home:impl")

include(":feature_search")
include(":feature_search:api")
include(":feature_search:impl")

include(":feature_profile")
include(":feature_profile:api")
include(":feature_profile:impl")

include(":feature_root")
include(":core_view")
