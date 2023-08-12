# kotlinx-protobuf-gen

Generate kotlin data classes from `protobuf` files that supports _Kotlin Native_ that can be serialized and
deserialized to protobuf using [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization).

> [!WARNING]  
> This project is still WIP. I am making this repository public to cross-share my effort to run conformance
> tests against the `kotlinx.serialization` library. I am able to identify some underlying bugs in the library
> and I am working on fixing them. I will make a first release once I am able to pass all conformance tests.
>
> Feel free to open PRs and issues to contribute in the meanwhile.

## Example

To see examples on how to use this library, check out the [examples](./examples) directory.

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

## Features

- [x] Supports `proto2` and `proto3`.
- [x] Generates `kotlinx.serialization` annotations for proto field numbers and serialization format.
- [x] Generates Kotlin code for primitive fields such as `int32`, `string`, `bytes`.
- [x] Generates Kotlin code for `message`, `enum`, `repeated`, `map`, `oneof` types.
- [x] Generates Kotlin code that includes imports and uses nested types.

## Roadmap

I will probably move those stuff to issues and projects later on. For now, here is a list of stuff that I have
been thinking about,

Our goal is to eventually support all features of Protobuf in Kotlin without depending on the Java library. Here
is a list of features we are working on that are required to release first stable version.

- [ ] Proper serialization / deserialization of all types. Check "Known Issues" section below to see all major
  issues.
- [ ] Make data classes with `ByteArray` implement equals and hashcode correctly.
- [ ] Run full conformance tests on the generated code.

And here is a list of features that we are planning to work on after the first stable release.

- [x] Support Well-Known Types deserialization to Well-Known Kotlin types such as `google.protobuf.Duration`
  to `kotlin.time.Duration` and `google.protobuf.Timestamp` to `kotlinx.datetime.Instant`.
  - An option is added to code generator to enable this feature.
- [ ] Support various options such as `deprecated`, `default`, `json_name`.
- [ ] Auto-generated comments from `.proto` files in the generated code.
- [ ] Support Protobuf JSON format by default.
- [x] gRPC support.
  - Stub generation is completed but it does not include any functionality to call or receive gRPC yet.
- [ ] Plugin and more option support for customizing the generated code. (Such as non-enforced nullability to
  gimmick proto2 required fields based on certain rules)

## Known Issues

An issue to track `kotlinx.serialization`: https://github.com/Kotlin/kotlinx.serialization/issues/2401

Focusing on core functionality, here is a list of known major issues:

- [x] Generated `oneof` fields are flattened and not serialized correctly.
  - A flat list of oneof fields is generated. Validation happen in `init` block to make sure at most one
    field is set. One caveat is overlapping names which we can consider later.
  - Will consider sealed traits in the future.
- [ ] Generated `repeated` fields with `fixedXX`, `sfixedXX` and `uintXX` types can't be serialized.
- [ ] Generated `repeated` fields with `sintXX` deserializes incorrectly.
- [ ] Generated `map` fields with `fixedXX` and `sfixedXX` keys can't be serialized.
- [ ] Generated `enum` fields with negative values can't be serialized / deserialized.
  - PR opened in `kotlinx.serialization`: https://github.com/Kotlin/kotlinx.serialization/pull/2400
