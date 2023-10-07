import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

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

repositories {
    mavenCentral()
    maven {
        this.url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

var protobufVersion = "3.23.4"
val grpcVersion = "1.58.0"
val grpcKotlinVersion = "1.3.1"

dependencies {
    implementation("io.github.dogacel:kotlinx-protobuf-gen-runtime:alpha-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    listOf(
        "armeria",
        "armeria-grpc",
        "armeria-logback",
        "armeria-kotlin"
    ).forEach {
        implementation("com.linecorp.armeria:$it:1.25.2")
    }

    // GRPC
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")

    // Logging
    runtimeOnly("ch.qos.logback:logback-classic:1.4.11")
    runtimeOnly("org.slf4j:log4j-over-slf4j:1.7.36")
}

configurations.all {
    resolutionStrategy.sortArtifacts(ResolutionStrategy.SortOrder.DEPENDENCY_FIRST)
}

tasks.named("generateProto") {
    dependsOn(project(":app").tasks.jar)
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
//            artifact = "io.github.dogacel:kotlinx-protobuf-gen:alpha-SNAPSHOT:jvm8@jar"
            path = project(":app").tasks.jar.get().archiveFile.get().asFile.absolutePath
        }
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar"
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
            val condition =
                entry.file.toString().contains(".proto.kt") || entry.file.toString().contains("generated")
            condition
        }
    }
}
