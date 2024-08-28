pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri ("https://jitpack.io") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }

    }
}

pluginManagement {
    buildscript {
        repositories {
            mavenCentral()
            maven {
                // r8 maven
                url = uri("https://storage.googleapis.com/r8-releases/raw")
            }
        }
        dependencies {
            // r8 version
            classpath("com.android.tools:r8:8.2.16-dev")
        }
    }
}

rootProject.name = "Diaries"
include(":app")
 