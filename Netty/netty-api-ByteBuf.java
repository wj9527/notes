-----------------------------
ByteBuf						 |
-----------------------------
	#  abstract class ByteBuf implements ReferenceCounted, Comparable<ByteBuf>

-----------------------------
·½·¨						 |
-----------------------------
	long  readUnsignedInt()
	ByteBuf retainedDuplicate()

	short readUnsignedByte()

	ByteBuf markReaderIndex()
	ByteBuf resetReaderIndex()