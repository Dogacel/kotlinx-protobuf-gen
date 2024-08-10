import com.google.protobuf.gradle.id

plugins {
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization")
    id("com.google.protobuf")
    application
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")

    // Publish
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.protobuf)
    implementation(libs.bundles.kotlinx)
    implementation(libs.kotlinpoet)

    testImplementation(libs.bundles.junit)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.withType(Test::class.java) {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

application {
    // Define the main class for the application.
    mainClass.set("dogacel.kotlinx.protobuf.gen.AppKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }

    from(sourceSets.main.get().output)

    val runtimeClasspathJars = configurations.runtimeClasspath.get().filter { it.name.endsWith(".jar") }
    from(runtimeClasspathJars.map { zipTree(it) })

    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    archiveClassifier.set("jvm8")
}

kover {
    reports {
        filters {
            includes {
                this.packages("dogacel.kotlinx.protobuf.gen")
                this.packages("dogacel.kotlinx.protobuf.gen.*")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("$rootDir/testProtos")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${libs.versions.protobuf.get()}"
    }

    // Enable Kotlin generation
    generateProtoTasks {
        all().forEach {
            it.builtins {
                id("kotlin")
            }
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
            artifactId = "kotlinx-protobuf-gen"
            version = rootProject.version.toString()

            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("kotlinx-protobuf-gen")
                description.set("Generate kotlin classes using kotlinx.serialization from proto definitions.")
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
