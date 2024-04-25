import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}

group = "com.epam.drill"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
}

application {
    mainClass.set("com.epam.drill.github.action.ExecuteMakePrCommentsKt")
}

tasks {
    jar {
        archiveBaseName.set("app")
        archiveVersion.set("")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)
        manifest {
            attributes("Main-Class" to "com.epam.drill.github.action.ExecuteMakePrCommentsKt")
        }
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    withType<JavaCompile> {
        targetCompatibility = "1.8"
    }
}

dependencies {
    //INFO: All dependencies for Kotlin 1.5.31
    implementation("com.squareup.moshi:moshi:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    //Logging:
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
    implementation("ch.qos.logback:logback-classic:1.2.6")
}
