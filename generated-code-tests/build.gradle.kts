import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.google.protobuf")
    kotlin("plugin.serialization")
    id("org.jetbrains.kotlinx.kover")
    id("org.jlleitschuh.gradle.ktlint")
    application
}

repositories {
    mavenCentral()
}

var protobufVersion = "3.23.4"

dependencies {
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")
    implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.6.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

tasks.withType(KotlinCompilationTask::class.java).configureEach {
    compilerOptions.freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
}

tasks.named("generateProto") {
    dependsOn(project(":app").tasks.jar)
    dependsOn("cleanSources")
}

tasks.register("copySources", type = Sync::class) {
    dependsOn("generateProto")
    from("$buildDir/generated/source/proto/main/kotlinx-protobuf-gen")
    into("src/main/kotlin")
}

tasks.register("cleanGeneratedFiles", type = Delete::class) {
    dependsOn("copySources")
    delete("$buildDir/generated/source/proto/main/kotlinx-protobuf-gen")
}

tasks.register("cleanSources") {
    doFirst {
        delete("src/main/kotlin")
    }
}

tasks.named("compileKotlin") {
    dependsOn("cleanGeneratedFiles")
}

sourceSets {
    main {
        proto {
            srcDir("$rootDir/testProtos")
        }
    }
}

koverReport {
    filters {
        includes {
            this.packages("testgen")
            this.packages("testgen.*")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }

    plugins {
        id("kotlinx-protobuf-gen") {
            path = project(":app").tasks.jar.get().archiveFile.get().asFile.absolutePath
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("kotlinx-protobuf-gen") {
                    option("package_prefix=testgen")
                }
            }
        }
    }
}

// Lint

tasks.named("runKtlintCheckOverMainSourceSet") {
    dependsOn("cleanGeneratedFiles")
}

ktlint {
    ignoreFailures.set(true)
    filter {
        exclude { entry ->
            val condition = entry.file.toString().contains("generated") ||
                    entry.file.toString().contains("testgen")
            if (!condition) {
//                println(entry.file)
            }
            condition
        }
    }
}
