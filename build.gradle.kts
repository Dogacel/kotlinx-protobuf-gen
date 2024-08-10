plugins {
    `maven-publish`
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

group = "io.github.dogacel"
version = "0.0.01"

nexusPublishing {
    this.repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    repositories {
        // Required to download KtLint
        mavenCentral()
    }

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        ignoreFailures.set(true)
        filter {
            exclude { entry ->
                val condition =
                    entry.file.toString().contains(".proto.kt") || entry.file.toString().contains("generated")
                condition
            }
        }
    }
}
