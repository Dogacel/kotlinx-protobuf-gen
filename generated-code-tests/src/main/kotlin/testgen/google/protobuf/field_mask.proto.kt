package testgen.google.protobuf

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class FieldMask(
  @ProtoNumber(number = 1)
  public val paths: List<String> = emptyList(),
)
