package testgen.test

import kotlin.ByteArray
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
public data class BytesUnderTest(
  @ProtoNumber(number = 1)
  public val bytes: ByteArray? = byteArrayOf(),
)
