import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar


plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    application
}

group = "yes.mediumdifficulty"
version = "2.1.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.1-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-api:4.9.3")
    implementation("net.kyori:adventure-platform-bungeecord:4.0.1")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

tasks.withType<ShadowJar> {
    relocate("net.kyori", "yes.mediumdifficulty.statgraphs.libs.net.kyori")
    dependencies {
        exclude(dependency("org.jetbrains.kotlin:.*"))
    }
    destinationDirectory.set(file("run\\plugins"))
}

application {
    mainClass.set("MainKt")
}