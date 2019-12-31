-------------------------
jpql
-------------------------
	# 参数的绑定
		* 通过位置绑定	WHERE id = ?1
		* 通过名称绑定	WHERE id = :id
	
	# JPQL的修改要在事务下进行否则可能异常: InvalidDataAccessApiUsageException
		 Executing an update/delete query; nested exception is javax.persistence.TransactionRequiredException: Executing an update/delete query

	# 支持返回对象使用 Optional 封装


----------------
JPQL检索语法
----------------
	# 语法
		* 操作的是对象 @Entity, 不是表, 操作的是对象属性, 也不是字段
		* 默认Entity名称就是类名称大写, 也可以通过修改 @Entity 属性的注解来修改

		* 不支持使用 `` 符号, 不支持在末尾添加分号: ;
		* 支持使用 AS 关键字起别名

	
	# LIKE
		FROM User u WHERE u.name LIKE %:name%
	
	# IN
		FROM User u WHERE u.id in :ids
			* ids是一个集合
	
	# COUNT
		SELECT COUNT(1) FROM User AS u WHERE u.name LIKE %:#{#user.name}%
	
	# ORDER BY
		FROM User u WHERE u.id = :id ORDER BY id DESC
	
	# INSERT INTO 
		//TODO
	
	# 使用 new 关键字来创建指定类型的返回对象
		SELECT new io.springboot.jpa.domain.model.UserModel(u.name) FROM User AS u WHERE u.id = :id
		
			* 通过构造方法, 来创建对象
			* 如果对象是被EntityManager管理的, 可以只写其类名, 否则需要声明完整的类路径
			* 通过这种方式可以做到: 仅仅只检索部分字段
		
	# 使用Map作为检索结果

		SELECT new map(u.name AS name, u.id AS id) FROM User AS u WHERE id = :id
			* 需要对检索的列, 使用AS起别名, 作为key的名称
			* 多行多列返回的结果类型是: List<Map<String, Object>> (任何情况都适用)
			* 单行单列返回的结果类型是: Map<String, Object>

			* 如果不设置别名, 单行单列的情况下, key = null, 多行的情况下, key = 序号(从0开始)
				{
					"0":"Kevin",
					"1":1
				}
	
	# 仅仅检索部分字段
		* 上述的 new 对象方法和使用Map作为结果集就可以完成
	
	
	# 仅仅检索单个字段
		SELECT u.name FROM User AS u WHERE u.id = :id

			* 返回值类型, 就根据字段的类型定义
	
	# 检索单个/多个字段的总结
		* 单个字段单条记录时, 返回类型最好用对应字段的类型或者 Object。
		* 单个子弹多条记录时, 返回类型最好用 List<Object> 或者 List<字段对应类型>
		* 多个字段时, 不论是多条记录还是单条记录, 返回类型都应该是 List<Object[]>
	

	# 定义常量属性
		SELECT new UserModel('unknow' AS name, u.id) FROM User AS u
			
			* 通过 默认值 AS 属性 定义

	
