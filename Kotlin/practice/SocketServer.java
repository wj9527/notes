import java.io.Serializable
import java.net.InetSocketAddress
import java.net.StandardSocketOptions
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

class Message : Serializable {

    private val header : ByteBuffer = ByteBuffer.allocate(4)

    constructor(){}

    fun getHeader() : ByteBuffer{
        return this.header
    }

    fun getBodyLength(): Int{
        if(this.header.hasRemaining()){
            // 不足四个4字节
            return -1
        }
        return this.header.int
    }
}

fun main(){

    val selector = Selector.open()

    val serverSocketChannel = ServerSocketChannel.open()
    serverSocketChannel.bind(InetSocketAddress("0.0.0.0", 1024))
    serverSocketChannel.configureBlocking(false)
    serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true)

    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)

    while (selector.select() > 0){

        val selectionKeyIterator = selector . selectedKeys ().iterator()

        while(selectionKeyIterator.hasNext()){

            val selectionKey = selectionKeyIterator.next()

            selectionKeyIterator.remove()

            when {
                selectionKey.isAcceptable -> {
                    val socketChannel = (selectionKey.channel() as ServerSocketChannel).accept()
                    socketChannel.configureBlocking(false)
                    socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true)

                    socketChannel.register(selector, SelectionKey.OP_READ or SelectionKey.OP_WRITE, Message())
                    // TODO 广播消息
                    // TODO 会话存储

                }
                selectionKey.isReadable -> {
                    val socketChannel = selectionKey.channel() as SocketChannel
                }
                selectionKey.isWritable -> {

                }
                selectionKey.isConnectable -> {

                }
            }
        }
    }
}

