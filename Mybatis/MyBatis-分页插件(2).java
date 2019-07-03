-----------------------
MyBatis-分页插件		|
-----------------------
	# maven
		<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper</artifactId>
		</dependency>

-------------------------
MyBatis-PageRowBounds分页|
-------------------------
	# mybatis配置
		<plugin interceptor="com.github.pagehelper.PageInterceptor">
			<!-- 方言 -->
			<property name="helperDialect" value="mysql"/>
			<!-- RowBounds对象的offset属性做为分页的page参数 -->
			<property name="offsetAsPageNum" value="true"/>
			<!-- 分页合理化参数，防止因为页码头尾溢出而检索到空记录 -->
			<property name="reasonable" value="true"/>
			<!-- 当 pageSize(limit) = 0 忽略页码，直接检索出所有的记录 -->
			<property name="pageSizeZero" value="true"/>
			<!-- RowBounds 作为分页参数时需要检总记录数 -->
			<property name="rowBoundsWithCount" value="true"/>
		</plugin>
	
	# 代码

		// SQL参数过滤对象
		UserEntity userEntity = new UserEntity();
		
		// 页码和每页显示数量
		PageRowBounds pageRowBounds = new PageRowBounds(2, 1);

		// 是否要检索总记录数
		pageRowBounds.setCount(true);
		
		List<UserEntity> users = this.userMapper.pageTest(userEntity ,pageRowBounds);
		
	
		* pageRowBounds 的属性
			{
				"count":true,			// 是否检索总记录数量
				"limit":1,				// 每页显示数量
				"offset":2,				// 当前页码
				"total":3				// 总记录数量
			}
				
			