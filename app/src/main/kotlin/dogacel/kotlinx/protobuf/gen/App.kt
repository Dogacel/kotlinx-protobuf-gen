package dogacel.kotlinx.protobuf.gen

import com.google.protobuf.compiler.PluginProtos

fun main() {
    val request = PluginProtos.CodeGeneratorRequest.parseFrom(System.`in`)
    val options = CodeGeneratorOptions.parse(request.parameter)
    val specs = CodeGenerator(request = request, options = options).generateFileSpecs()

    val responseBuilder =
        PluginProtos.CodeGeneratorResponse.newBuilder()
            .setSupportedFeatures(PluginProtos.CodeGeneratorResponse.Feature.FEATURE_PROTO3_OPTIONAL_VALUE.toLong())
            .addAllFile(
                specs.map { spec ->
                    PluginProtos.CodeGeneratorResponse.File.newBuilder()
                        .setName(spec.packageName.replace('.', '/') + "/" + spec.name + ".kt")
                        .setContent(spec.toString())
                        .build()
                }
            )
            .build()

    responseBuilder.writeTo(System.out)
}
