import com.google.protobuf.gradle.id

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.google.protobuf")
    kotlin("plugin.serialization")
    application
}

application {
    // Define the main class for the application.
    mainClass.set("AppKt")
}

dependencies {
    implementation(project(":runtime-common"))

    implementation(libs.bundles.kotlinx)

    listOf(
        "armeria",
        "armeria-grpc",
        "armeria-logback",
        "armeria-kotlin",
    ).forEach {
        implementation("com.linecorp.armeria:$it:${libs.versions.armeria.get()}")
    }

    // GRPC
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.grpc.protobuf)
    implementation(libs.bundles.protobuf)

    // Logging
    runtimeOnly(libs.bundles.logging)
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
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${libs.versions.grpc.asProvider().get()}"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${libs.versions.grpc.kotlin.get()}:jdk8@jar"
        }
    }

    // Enable Kotlin generation
    generateProtoTasks {
        all().forEach {
            it.builtins {
                id("kotlin")
            }
            it.plugins {
                id("grpc")
                id("grpckt")
                id("kotlinx-protobuf-gen")
            }
        }
    }
}

ktlint {
    filter {
        exclude { entry ->
            val condition = entry.file.toString().contains(".proto.kt") || entry.file.toString().contains("generated")
            condition
        }
    }
}
