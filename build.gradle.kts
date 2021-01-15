plugins {
    kotlin("jvm") version "1.4.21"
}

group = "me.dreamhopping"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib"))

    // Clikt
    implementation("com.github.ajalt.clikt:clikt:3.1.0")

    // ASM
    implementation("org.ow2.asm:asm-tree:9.0")
    implementation("org.ow2.asm:asm-commons:9.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}
