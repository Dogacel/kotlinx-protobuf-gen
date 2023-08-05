# kotlinx-protobuf-gen

A customizable *protoc* plugin to generate kotlin case classes from protobuf files that
use [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) annotations.

> [!WARNING]  
> This project is still WIP. There are some underlying bugs in `kotlinx.serialization` that prevent this plugin
> from working correctly. I am making this repository public to cross-share my effort to run conformance tests
> on the `kotlinx.serialization` library. I will make this plugin work as soon as the underlying **major** bugs
> are fixed.
