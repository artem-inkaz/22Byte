buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dep.gradle_plugin)
        classpath(Hilt.gradlePlugin)
        classpath(Navigation.safeArgs_gradle)
        classpath(Analytics.google_services)
        classpath(Analytics.gradle_firebase)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application").version("7.1.0").apply(false)
    id("com.android.library").version("7.1.0").apply(false)
    id("org.jetbrains.kotlin.android").version("1.6.10").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}