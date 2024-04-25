rootProject.name = "drill4j-report-github-action"

pluginManagement {
    plugins {
        kotlin("jvm") version "1.5.31"
        id("com.github.hierynomus.license") version "0.16.1"
    }

    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
