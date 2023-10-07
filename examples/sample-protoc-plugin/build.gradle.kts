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
    implementation("io.github.dogacel:kotlinx-protobuf-gen-runtime:alpha-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
}

configurations.all {
    resolutionStrategy.sortArtifacts(ResolutionStrategy.SortOrder.DEPENDENCY_FIRST)
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

ktlint {
    filter {
        exclude { entry ->
            val condition =
                entry.file.toString().contains(".proto.kt") || entry.file.toString().contains("generated")
            condition
        }
    }
}
