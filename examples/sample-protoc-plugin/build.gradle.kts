import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.remove
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.google.protobuf")
    kotlin("plugin.serialization")
    application
}

repositories {
    mavenCentral()
    maven {
        this.url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

var protobufVersion = "3.23.4"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.6.0-RC")
}

tasks.withType(KotlinCompilationTask::class.java).configureEach {
    compilerOptions.freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }

    plugins {
        id("kotlinx-protobuf-gen") {
            artifact = "io.github.dogacel:kotlinx-protobuf-gen:alpha-SNAPSHOT:jvm8@jar"
        }
    }

    // Enable Kotlin generation
    generateProtoTasks {
        all().forEach {
            it.builtins {
                remove("java")
            }
            it.plugins {
                id("kotlinx-protobuf-gen") {
                    option("package_prefix=custom.pkg")
                }
            }
        }
    }
}
