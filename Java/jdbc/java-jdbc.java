------------------------------
JDBC						  |
------------------------------
	# JDBC是由一些接口构成的API(Java Data Base Connectivity,java数据库连接);
	# 连接数据库的步骤
		①注册驱动(只做一次)
		②建立连接(Connection)
		③创建执行SQL语句(Statement)
		④执行语句
		⑤处理执行结果(ResultSet)
		⑥释放资源
	# MySql——url格式
		* JDBC:子协议:子名称//主机名:端口/数据库名?属性名=属性值&
		* 本机连接:"jdbc:mysql://www.cqsic.com:3366/wuyedb?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true"
		
------------------------------
JDBC-API					  |
------------------------------
	DriverManager
		# 静态方法
			Connection getConnection(String url, String user, String password) ;
				* 根据URL用户密码,获取一个连接
	Connection
		# 代表一个数据库连接
		# 实例方法
			Statement createStatement(); 
				* 创建一个Statement对象
			PreparedStatement prepareStatement(String sql); 
				* 根据SQL出创建一个PreparedStatement对象
			CallableStatement prepareCall(String sql);
				* 创建一个CallableStatement对象来调用数据库存储过程
			setAutoCommit(boolean transaction);
				* 如果该值为 false 则开启手动事务,需要手动执行 commit 才会提交
			commit();
				* 提交事务
			rollback();
				* 回滚事务

	Statement
		# 是一个接口,可以使用它来执行SQL语句
		# 实例方法
			boolean execute(String sql) ;
				* 执行SQL
			ResultSet executeQuery(String sql); 
				* 执行给定的 SQL 检索 语句，该语句返回单个 ResultSet 对象。 
			int executeUpdate(String sql) 
				* 执行给定 SQL 语句，该语句可能为 INSERT、UPDATE 或 DELETE 语句，或者不返回任何内容的 SQL 语句（如 SQL DDL 语句）。 
				* 返回受影响的行数
		|-PreparedStatement
			# 预编译SQL的,statement,可以防止SQL注入
			# 实例方法
				 setString(int index, String param);
					* 给第一个问号赋值
					* 有N多重载(setXxx),可以赋值不同的数据类型
					* 注意'?'号的角标是从1开始,而不是0
				boolean execute() 
					* 执行SQL语句该语句可以是任何种类的 SQL 语句。 
				ResultSet executeQuery();
					* 执行检索,并返回该查询生成的 ResultSet 对象。 
				int executeUpdate()  
					* 执行插入或者修改语句

		|-CallableStatement
			# 调用存储过程的 statement
	ResultSet
		# SQL执行结果集
		# 实例方法
			first() 
				* 将光标移动到此 ResultSet 对象的第一行。
			next();
				* 指针移到下一行,如果有下一行返回 true,反之返回 false
			previous(); 
				* 指针移到上一行
			getObject(int columnIndex);
				* 获取指定 index 列的数据,以Object形式返回
			getObject(String columnLabel);
				* 获取指定 列名称 的数据,以Object形式返回
			getString(String columnName); 
				* 获取指定字段的String类型值
			getString(int columnIndex); 
				* 获取指定索引的String类型值
			* 有大量的 getXxx();存在,包含了JAVA各种基本数据类型的返回值

	Blob 
		# 二进制数据对象
		# 构造
			Blob blob = new SeriaBlob(byte[] bytes);	
		# 实例方法
			InputStream blob.getBinaryStream();
				* 获取该 Blob 对象中的读取流
		

------------------------------
JDBC-操作二进制数据			  |
------------------------------
	# "标准SQL"中提供了如下类型来保存大的二进制数据类型
		tinyblob	256B	-- 二进制(字节)
		blob		64k
		mediumblob	16M
		longblob	4G
	# "标准SQL"中提供了如下类型来保存大的文本数据类型
		tinyclob	256B
		clob		645KB
		mediumclob	16MB
		longclob	4G

	# "MYSQL"中定义的大数据字符类型
		tinytext	256B
		text		64KB
		mediumtext	16MB
		longtext	4G
	
		* 一般,往MYSQL中存入过大数据的时候需要在在my.ini中最后添加配置
		* max.allowed packet=10485760
		* 表示设置MYSQL的数据存储大小
	
	# JAVA操作二进制数据
		* 主要是 Blob blob = new SeriaBlob(byte[] bytes); 对象
		* 存入
			public static void set()throws Exception 
			{
				Connection conn = JDBCUtils.getConnection();
				String sql = "insert into tab_bin values(?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1);				//数据库ID字段
				pstmt.setString(2, "测试.mp3");	//名称
				/**
				 * 需要得到Blob对象
				 * 1,有文件,目标是Blob
				 * 3,把文件变成byte[]数组
				 * */
				File f = new File("D:\\Demo.mp3");
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
				byte[] bytes = new byte[in.available()];//创建文件大小的字节数组
				in.read(bytes);						
				Blob blob = new SerialBlob(bytes);
				pstmt.setBlob(3, blob);//二进制文件
				pstmt.executeUpdate();
			}
		* 读取
			public static void get()throws Exception 
			{
				//获得连接对象
				Connection conn = JDBCUtils.getConnection();
				String sql = "select * from tab_bin";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//pstmt执行查询得到ResultSet
				ResultSet rs = pstmt.executeQuery();
				//获取rs中名为Date的列数据
				if(rs.next())
				{
					Blob blob = rs.getBlob("data");
					/**
					 * 把 blob变回硬盘上的文件
					 * */
					InputStream in = blob.getBinaryStream();
					BufferedOutputStream bufr = new BufferedOutputStream(new FileOutputStream(new File("C:\\test.mp3")));
					byte[] b = new byte[1024];
					int len = 0;
					while((len = in.read(b)) != -1)
					{
						bufr.write(b, 0, len);
						bufr.flush();
					}
				}
			}