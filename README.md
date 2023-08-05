# kotlinx-protobuf-gen

A customizable *protoc* plugin to generate kotlin case classes from protobuf files that
use [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) annotations.

> [!WARNING]  
> This project is still WIP. There are some underlying bugs in `kotlinx.serialization` that prevent this plugin
> from working correctly. I am making this repository public to cross-share my effort to run conformance tests
> on the `kotlinx.serialization` library. I will make this plugin work as soon as the underlying **major** bugs
> are fixed.

## Example

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

is converted to

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
