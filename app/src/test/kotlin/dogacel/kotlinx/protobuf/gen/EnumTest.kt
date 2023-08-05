/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package dogacel.kotlinx.protobuf.gen

import enums.Enums
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import testgen.enums.MessageWithEnum
import kotlin.io.path.Path
import kotlin.test.Test

class EnumTest {
    @Test
    fun shouldWork() {
        val codeGenerator = CodeGenerator(
            enums.Enums.getDescriptor(), options = CodeGeneratorOptions(
                packagePrefix = "testgen"
            )
        )
        codeGenerator.generate()
    }

    @Test
    fun shouldDeSerEnum() {
        Enums.TestEnum.entries.filterNot { it == Enums.TestEnum.UNRECOGNIZED }.forEach {
            println(it.name)
            val someProtoEnum = it
            val someWrapperMessage = enums.Enums.MessageWithEnum.newBuilder()
                .setTestEnum(someProtoEnum)
                .build()

            val bytes = someWrapperMessage.toByteArray()

            val result: MessageWithEnum = ProtoBuf.decodeFromByteArray(bytes)
            println(result)
        }
    }

    @Test
    fun shouldDeSerAliasEnum() {
        (Enums.AliasedEnum.entries
                + listOf(
            Enums.AliasedEnum.QUX,
            Enums.AliasedEnum.qux,
            Enums.AliasedEnum.bAz,
        ))
            .filterNot { it == Enums.AliasedEnum.UNRECOGNIZED }.forEach {
                println(it.name)
                val someProtoEnum = it
                val someWrapperMessage = enums.Enums.MessageWithEnum.newBuilder()
                    .setAliasedEnum(someProtoEnum)
                    .build()

                val bytes = someWrapperMessage.toByteArray()

                val result: MessageWithEnum = ProtoBuf.decodeFromByteArray(bytes)
                println(result)
            }
    }
}
