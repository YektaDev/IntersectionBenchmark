import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    application
}

group = "dev.yekta"
version = "1.0.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("BenchmarkKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}
