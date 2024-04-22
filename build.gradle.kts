plugins {
    kotlin("jvm")
}
group = "com.epam.drill"
version = "1.0.0"


repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("com.squareup.moshi:moshi:1.9.3")
    implementation("com.squareup.moshi:moshi-kotlin:1.9.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("com.natpryce:hamkrest:1.7.0.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.8.1")
    implementation(kotlin("script-runtime"))
}

