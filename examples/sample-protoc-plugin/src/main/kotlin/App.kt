import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToHexString
import kotlinx.serialization.protobuf.ProtoBuf
import protos.Node
import protos.NodeStatus

fun main() {
    val myNode = Node(
        id = 100,
        name = "Network Node",
        tags = listOf("fast", "reliable"),
        attributes = mapOf(
            "ip" to "192.168.1.1",
            "host" to "9000",
        ),
        status = NodeStatus.ACTIVE,
        next = Node(
            id = 200,
            name = "Network Node 2"
        )
    )

    println(myNode)

    ProtoBuf.encodeToHexString(myNode).also { println(it) }
}
