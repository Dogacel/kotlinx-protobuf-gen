package dogacel.kotlinx.protobuf.gen

import messages.MessageNoFieldsOuterClass.MessageNoFields
import kotlin.test.Test
import kotlin.test.assertEquals

class CodeGeneratorTest {

    @Test
    fun hasAnyFieldShouldWork() {
        val codeGenerator = CodeGenerator()

        val messageNoFields = MessageNoFields.getDescriptor()
        val messageSomeFields1 = MessageNoFields.SubMessageNoFields.getDescriptor()
        val messageSomeFields2 = MessageNoFields.SubMessageNoFields.SubMessageNoFieldsExtend.getDescriptor()
        val messageSomeFieldsOneof = MessageNoFields.SubMessageOneofFields.getDescriptor()

        assertEquals(true, codeGenerator.hasNoField(messageNoFields))
        assertEquals(false, codeGenerator.hasNoField(messageSomeFields1))
        assertEquals(false, codeGenerator.hasNoField(messageSomeFields2))
        assertEquals(false, codeGenerator.hasNoField(messageSomeFieldsOneof))
    }
}
