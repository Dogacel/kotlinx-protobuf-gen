import com.google.protobuf.gradle.id

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.google.protobuf")
    kotlin("plugin.serialization")
    id("org.jetbrains.kotlinx.kover")
    application
}

repositories {
    mavenCentral()
}


dependencies {
    implementation(libs.bundles.protobuf)
    implementation(libs.bundles.kotlinx)
    implementation(project(":runtime-common"))

    testImplementation(libs.bundles.junit)
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

kover {
    reports {
        filters {
            includes {
                this.packages("testgen")
                this.packages("testgen.*")
            }
        }
    }
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

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("kotlinx-protobuf-gen") {
                    option("package_prefix=testgen")
                    option("use_well_known_types")
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
    filter {
        exclude { entry ->
            val condition =
                entry.file.toString().contains(".proto.kt") || entry.file.toString().contains("generated")
            condition
        }
    }
}
