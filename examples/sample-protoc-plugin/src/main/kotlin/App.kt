import custom.pkg.protos.Node
import custom.pkg.protos.NodeStatus
import kotlinx.serialization.encodeToHexString
import kotlinx.serialization.protobuf.ProtoBuf

fun main() {
    val myNode = Node(
        id = 100,
        name = "Network Node",
        tags = listOf("fast", "reliable"),
        attributes = mapOf(
            "ip" to "192.168.1.1",
            "host" to "9000"
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
