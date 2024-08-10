plugins {
    kotlin("jvm") version "2.0.0" apply false
    kotlin("plugin.serialization") version "2.0.0" apply false

    id("com.gradle.develocity") version ("3.17.6")
    id("com.google.protobuf") version "0.9.4" apply false
    id("org.jetbrains.dokka") version "1.9.20" apply false
    id("org.jetbrains.kotlinx.kover") version "0.8.3" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1" apply false
    id("ru.vyarus.mkdocs") version "3.0.0" apply false
}

rootProject.name = "kotlinx-protobuf-gen"

// Published artifacts
include("app")
include("runtime-common")

// Tests
include("generated-code-tests")
include("examples:sample-protoc-plugin")
include("examples:sample-grpc-server")

// Documentation
include("docs")

develocity {
    buildScan {
        termsOfUseUrl.set("https://gradle.com/terms-of-service")
        termsOfUseAgree.set("yes")
    }
}
