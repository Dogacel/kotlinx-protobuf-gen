package testgen.comments.test

import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

/**
 * A documented message.
 * This message is used for testing comment extraction.
 *
 * @param id The unique ID of the message.
 * @param name The name field.
 * @param description Trailing comment for description.
 * @param nested The nested message field.
 */
@Serializable
public data class DocumentedMessage(
  @ProtoNumber(number = 1)
  public val id: Int = 0,
  @ProtoNumber(number = 2)
  public val name: String = "",
  @ProtoNumber(number = 3)
  public val description: String = "",
  @ProtoNumber(number = 4)
  public val nested: NestedMessage? = null,
) {
  /**
   * A nested message with comments.
   *
   * @param value The value in the nested message.
   */
  @Serializable
  public data class NestedMessage(
    @ProtoNumber(number = 1)
    public val `value`: Int = 0,
  )
}

/**
 * An undocumented message for comparison.
 */
@Serializable
public data class UndocumentedMessage(
  @ProtoNumber(number = 1)
  public val field1: Int = 0,
  @ProtoNumber(number = 2)
  public val field2: String = "",
)

/**
 * A message documented with C-style comments.
 *
 * @param id The ID field with inline block comment.
 * @param name A field with a multi-line block comment.
 */
@Serializable
public data class CStyleCommentMessage(
  @ProtoNumber(number = 1)
  public val id: Int = 0,
  @ProtoNumber(number = 2)
  public val name: String = "",
)

/**
 * A documented enum.
 * This enum represents different status values.
 */
@Serializable
public enum class DocumentedEnum {
  /**
   * The unknown status.
   */
  @ProtoNumber(number = 0)
  UNKNOWN,
  /**
   * The active status.
   */
  @ProtoNumber(number = 1)
  ACTIVE,
  /**
   * The inactive status.
   */
  @ProtoNumber(number = 2)
  INACTIVE,
  /**
   * Trailing comment for deprecated.
   */
  @ProtoNumber(number = 3)
  DEPRECATED,
}

/**
 * A documented service for testing.
 */
public abstract class DocumentedService {
  /**
   * Gets a documented message by ID.
   */
  public open suspend fun getMessage(documentedMessage: DocumentedMessage): DocumentedMessage = TODO()

  /**
   * Updates an existing documented message.
   */
  public open suspend fun updateMessage(documentedMessage: DocumentedMessage): DocumentedMessage = TODO()
}
