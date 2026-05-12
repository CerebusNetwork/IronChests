import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.cerebus.ironchests"
version = "0.1-alpha"

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.nova)
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.xenondevs.xyz/releases")
}

dependencies {
    compileOnly(libs.nova)
}

addon {
    name = "IronChests"
    version = project.version.toString()
    main = "io.cerebus.ironchests.IronChests"

    val outDir = project.findProperty("outDir")
    if (outDir is String)
        destination.set(File(outDir))
}

kotlin {
    jvmToolchain(25)
}

tasks {
    withType<KotlinCompile> {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_25
        }
    }
}

afterEvaluate {
    tasks.getByName<Jar>("jar") {
        archiveClassifier = ""
    }
}
