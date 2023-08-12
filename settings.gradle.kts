plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"

    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    kotlin("plugin.serialization") version "1.9.0" apply false
    id("com.google.protobuf") version "0.9.4" apply false
    id("org.jetbrains.kotlinx.kover") version "0.7.3" apply false

    id("org.jlleitschuh.gradle.ktlint") version "11.5.0" apply false
}

rootProject.name = "kotlinx-protobuf-gen"
include("app")
include("examples:sample-protoc-plugin")
