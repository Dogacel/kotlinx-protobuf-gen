import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.remove

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.google.protobuf")
    kotlin("plugin.serialization")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":runtime-common"))

    implementation(libs.bundles.kotlinx)
}

tasks.withType(Test::class.java) {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

tasks.named("generateProto") {
    dependsOn(project(":app").tasks.jar)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${libs.versions.protobuf.get()}"
    }

    plugins {
        id("kotlinx-protobuf-gen") {
            path = project(":app").tasks.jar.get().archiveFile.get().asFile.absolutePath
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
