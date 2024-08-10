# Customizing

To customize the code generated, you can pass command line arguments or gradle options. For example,

```kotlin
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }

    plugins {
        id("kotlinx-protobuf-gen") {
            artifact = "io.github.dogacel:kotlinx-protobuf-gen:0.0.1:jvm8@jar"
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

## Available Options

| Option             | Description                                                                                                | Default |
|--------------------|------------------------------------------------------------------------------------------------------------|---------|
| `package_prefix`   | Prefix for the generated package names. Appended to the start of each class                                | `""`    |
| `useCamelCase`     | Whether to use the original `snake_case` for proto fields or `camelCase`. Can be either `true` or `false`. | `true`  |
| `generateServices` | Whether to generate abstract gRPC stubs or not. Can be either `true` or `false`.                           | `true`  |
