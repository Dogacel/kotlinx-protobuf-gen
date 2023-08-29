# Setup

> To see more examples on how to use this library, check out the [examples](./examples) directory.

This document also includes details regarding the `protoc` gradle plugin setup to include
`.proto` files in your project. You can either directly compile proto sources in your kotlin project or
you can prefer to publish a JAR containing the sources and include it in your project.

Currently only available version is `alpha-SNAPSHOT`. In order to use it, you need to add sonatype snapshot
repository to your `build.gradle.kts` file,

```kotlin
repositories {
    mavenCentral()
    maven {
        this.url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}
```

#### 1. Dependencies,

```kotlin
plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("com.google.protobuf") version "0.9.4"
}

var protobufVersion = "3.23.4"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.6.0-RC")

    // Required if stream service definitions will be generated. Streaming RPC operations are
    // defined as Flow<...> types in code.
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Required if `bytes` type is used.
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")
    // Required for importing well-known types such as `com.google.protobuf.Timestamp`.
    implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")
    // Required for converting well-known types to kotlin Instant or Duration.
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
}
```

#### 2. Code generation,

```kotlin
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }

    plugins {
        id("kotlinx-protobuf-gen") {
            artifact = "io.github.dogacel:kotlinx-protobuf-gen:alpha-SNAPSHOT:jvm8@jar"
        }
    }

    // Enable Kotlin generation
    generateProtoTasks {
        all().forEach {
            it.builtins {
                remove("java") // Optionally you can keep the java generated files.
            }
            it.plugins {
                id("kotlinx-protobuf-gen") {
                    option("package_prefix=custom.pkg") // Set a custom package prefix
                }
            }
        }
    }
}
```

#### 3. Writing proto files,

Add your proto files to a known proto file path such as `src/main/proto`.

```protobuf
syntax = "proto3";

package demo;

message Task {
  int32 id = 1;
  optional string description = 2;
  Status status = 3;

  enum Status {
    WIP = 0;
    DONE = 1;
  }
}
```

The following class will be generated and added to your classpath.

```kotlin
@Serializable
public data class Task(
    @ProtoNumber(number = 1)
    public val id: Int = 0,
    @ProtoNumber(number = 2)
    public val description: String? = null,
    @ProtoNumber(number = 3)
    public val status: Status = testgen.demo.Task.Status.WIP,
) {
    @Serializable
    public enum class Status {
        @ProtoNumber(number = 0)
        WIP,

        @ProtoNumber(number = 1)
        DONE,
    }
}
```
