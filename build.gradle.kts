plugins {
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

nexusPublishing {
    this.repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            // ORG_GRADLE_PROJECT_sonatypeUsername
            // ORG_GRADLE_PROJECT_sonatypePassword
        }
    }
}

group = "io.github.dogacel"
version = "alpha-SNAPSHOT"

signing {
    val signingKey = providers
        .environmentVariable("GPG_SIGNING_KEY")
        .forUseAtConfigurationTime()
    val signingPassphrase = providers
        .environmentVariable("GPG_SIGNING_PASSPHRASE")
        .forUseAtConfigurationTime()
    if (signingKey.isPresent && signingPassphrase.isPresent) {
        useInMemoryPgpKeys(signingKey.get(), signingPassphrase.get())
        val extension = extensions
            .getByName("publishing") as PublishingExtension
        sign(extension.publications)
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
