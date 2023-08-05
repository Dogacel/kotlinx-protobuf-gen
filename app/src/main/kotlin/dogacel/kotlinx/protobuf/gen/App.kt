package dogacel.kotlinx.protobuf.gen

import kotlin.io.path.Path

fun main() {
    val codeGeneratorOptions = CodeGeneratorOptions(
        packagePrefix = "testgen"
    )
    val codeGenerator = CodeGenerator(
        primitives.Primitives.getDescriptor(),
        enums.Enums.getDescriptor(),
        repeateds.Repeateds.getDescriptor(),
        maps.Maps.getDescriptor(),
        options = codeGeneratorOptions
    )

    codeGenerator.generate(Path("./app/src/test/kotlin"))
}
