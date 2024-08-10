plugins {
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization")
    `java-library`
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")

    // Publishing
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.protobuf)
    implementation(libs.bundles.kotlinx)

    testImplementation(libs.bundles.junit)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.jar {
    from(sourceSets.main.get().output)

    val runtimeClasspathJars = configurations.runtimeClasspath.get().filter { it.name.endsWith(".jar") }
    from(runtimeClasspathJars.map { zipTree(it) })

    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    archiveClassifier.set("jvm8")
}

// sourceSets {
//    main {
//        proto {
//            srcDir("$rootDir/testProtos")
//        }
//    }
// }

// protobuf {
//    protoc {
//        artifact = "com.google.protobuf:protoc:$protobufVersion"
//    }
//
//    // Enable Kotlin generation
//    generateProtoTasks {
//        all().forEach {
//            it.builtins {
//                id("kotlin")
//            }
//        }
//    }
// }

ktlint {
    filter {
        exclude { entry ->
            val condition =
                entry.file.toString().contains(".proto.kt") || entry.file.toString().contains("generated")
            condition
        }
    }
}

// Publishing

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(kotlin.sourceSets.main.get().kotlin)
}

val javadocJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Javadoc JAR"
    archiveClassifier.set("javadoc")
    from(tasks.named("dokkaHtml"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = "kotlinx-protobuf-gen-runtime"
            version = rootProject.version.toString()

            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("kotlinx-protobuf-gen-runtime")
                description.set("Runtime classes for 'kotlinx-protobuf-gen'.")
                url.set("https://github.com/dogacel/kotlinx-protobuf-gen")
                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("dogacel")
                        name.set("Doğaç Eldenk")
                        email.set("dogacel@gmail.copm")
                    }
                }
                scm {
                    url.set(
                        "https://github.com/dogacel/kotlinx-protobuf-gen.git",
                    )
                }
                issueManagement {
                    url.set("https://github.com/dogacel/kotlinx-protobuf-gen/issues")
                }
            }
        }
    }
}

signing {
    val signingKey = providers.environmentVariable("GPG_SIGNING_KEY")
    val signingPassphrase = providers.environmentVariable("GPG_SIGNING_PASSPHRASE")

    if (signingKey.isPresent && signingPassphrase.isPresent) {
        useInMemoryPgpKeys(
            signingKey.get(),
            signingPassphrase.get(),
        )
        val extension = extensions.getByName("publishing") as PublishingExtension
        sign(extension.publications)
    }
}
