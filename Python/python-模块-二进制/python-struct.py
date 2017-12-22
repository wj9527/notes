---------------------------
struct						|
---------------------------
	* 二进制操作先相关的模块
	* 准确地讲,Python没有专门处理字节的数据类型
	* 但由于b'str'可以表示字节,所以, 字节数组＝二进制str
	* 在C语言中,我们可以很方便地用struct,union来处理字节,以及字节和int,float的转换。
	* 可以把数字,bool,字符串,打包为字节对象(二进制形式表示)

---------------------------
struct-属性					|
---------------------------

---------------------------
struct-模块方法				|
---------------------------
	bytes pack(fmt,v1,v2,v3....)
		* 把指定的数据,转换为字节数据
		* 的第一个参数是处理指令
			'>I'
				>表示字节顺序是big-endian,也就是网络序
				I表示4字节无符号整数
	
	unpack(fmt,buff)
		* 根据所给的fmt描述的格式将bytes反向解析出来,返回一个元组
		
	calcsize(fmt)
		* 根据所给的fmt描述的格式返回该结构的大小
		
---------------------------
struct-Struct				|
---------------------------
	* 模块类,提供面向对象的API
	* demo
		values = (1, 'abc', 2.7)
		s = struct.Struct('I3sf')
		packed_data = s.pack(*values)
		unpacked_data = s.unpack(packed_data)
	

---------------------------
struct-fmt对照表			|
---------------------------
	----------------------------------------------------------------------
	Format	C Type				Python type		Standard size	Notes		
	----------------------------------------------------------------------
	x		pad byte			no value	 	 
	c		char				bytes of length 1				1	 
	b		signed char			integer			1				(1),(3)
	B		unsigned char		integer			1				(3)
	?		_Bool				bool			1				(1)
	h		short				integer			2				(3)
	H		unsigned short		integer			2				(3)
	i		int					integer			4				(3)
	I		unsigned int		integer			4				(3)
	l		long				integer			4				(3)
	L		unsigned long		integer			4				(3)
	q		long long			integer			8				(2), (3)
	Q		unsigned long long	integer			8				(2), (3)
	n		ssize_t				integer	 						(4)
	N		size_t				integer	 						(4)
	e		(7)					float			2				(5)
	f		float				float			4				(5)
	d		double				float			8				(5)
	s		char[]				bytes	 	 
	p		char[]				bytes	 	 
	P		void *				integer	 						(6)



---------------------------
struct-demo					|
---------------------------
import struct

message = '卧槽。。。这个就算是带中文也没问题的哟。。'

bin_message = message.encode(encoding='utf_8', errors='strict')
# 
format = 'II%ds'%(len(bin_message))

packager = struct.Struct(format)

package = packager.pack(5,15,bin_message)

unpackage = packager.unpack(package)

print(unpackage)

print(unpackage[2].decode(encoding='utf_8', errors='strict'))
