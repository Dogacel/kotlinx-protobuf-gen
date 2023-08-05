package testgen.demo

import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

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
