import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization")
    id("com.google.protobuf")
    application
    `maven-publish`
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")
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

    implementation("com.squareup:kotlinpoet:1.14.2")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("dogacel.kotlinx.protobuf.gen.AppKt")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

tasks.withType(KotlinCompilationTask::class.java).configureEach {
    compilerOptions.freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
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

koverReport {
    filters {
        includes {
            this.packages("dogacel.kotlinx.protobuf.gen")
            this.packages("dogacel.kotlinx.protobuf.gen.*")
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
        artifact = "com.google.protobuf:protoc:$protobufVersion"
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
                        "https://github.com/dogacel/kotlinx-protobuf-gen.git"
                    )
                }
                issueManagement {
                    url.set("https://github.com/dogacel/kotlinx-protobuf-gen/issues")
                }
            }
        }
    }
}
