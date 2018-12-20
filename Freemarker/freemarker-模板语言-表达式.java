--------------------
取值表达式			|
--------------------
	# 直接取值
		* model属性
			${name}
			${user.name}  ${user["mame"]}
			${user.author.name} ${user["author"].name} ${user["author"]["name"]}

			* 如果属性名称里面有特殊的属性,需要使用转义字符
				表达式为 data\-id,因为 data-id 将被解释成 "data 减去 id"
				这些转义仅在标识符中起作用,而不是字符串中。
		
		* 常量
			${"这是一个字符串"}

		* 字符串支持转义
			\"		引号 (u0022)
			\'		单引号(又称为撇号) (u0027)
			\{		起始花括号：{
			\\		反斜杠 (u005C)
			\n		换行符 (u000A)
			\r		回车 (u000D)
			\t		水平制表符(又称为tab) (u0009)
			\b		退格 (u0008)
			\f		换页 (u000C)
			\l		小于号：<
			\g		大于号：>
			\a		&符：&
			\xCode	字符的16进制 Unicode 码 (UCS 码)
		
		
		* 数值

--------------------
值域				|
--------------------
		

--------------------
字符串的操作		|
--------------------
	# 字符串的拼接
		<#assign s = "Hello ${name}!"> //x = Kevin
		${s} //Hello Kevin
		
		<#assign s = "Hello " + user + "!">
		${s}//Hello Kevin
		
	
	# 根据索引获取字符
		${user[0]}		//获取字符串的第一个字符
		${user[4]}		//获取字符串的第三个字符
	
	# 字符串切割
		<#assign s = "ABCDEF">
		${s[2..3]}		CD		//从指定的角标到指定的角标
		${s[2..<4]}		CD		//从指定角标到指定角标一下
		${s[2..*3]}		CDE	
		${s[2..*100]}	CDEF
		${s[2..]}		CDEF	//从指定角标以后

		
		* 操作字符串的内置函数
			remove_beginning
			remove_ending
			keep_before
			keep_after
			keep_before_last
			keep_after_last


--------------------
字符串的操作		|
--------------------
	# 序列取值
		${user.skills[0].id}

	# 序列的连接
		<#list ["Joe", "Fred"] + ["Julia", "Kate"] as user>
			- ${user}
		</#list>	
	
	# 序列切分,使用 seq函数
		<#assert seq = ["A", "B", "C", "D", "E"]>
		<#list seq[1..3] as i>
			${i}		//输出BCD
		</#list>	
		
		* seq[1..3] 表示仅仅变量序列1-3角标的元素
		* 切分后序列中的项会和值域的顺序相同,那么上面的示例中,如果值域是 3..1 将会输出 'DCB'
		* 值域中的数字必须是序列可使用的合法索引
			- seq[-1..0]
			- seq[-1]
			- seq[1..5]	异常,因为5超出了最大角标4
			- seq[100..<100] 合法
			- seq[100..*0]	合法	

--------------------
Hash				|
--------------------
	# 连接
		<#assign ages = {"Joe":23, "Fred":25} + {"Joe":30, "Julia":18}>
		- Joe is ${ages.Joe}
		- Fred is ${ages.Fred}
		- Julia is ${ages.Julia}
	
		* 右侧的会覆盖前面的同名属性

--------------------
运算				|
--------------------		
	# 支持 +-*/%等运算
	# 字符串与数值相加,会自动转换数值为字符串
	# 字符串与数值执行除了相加以外的所有操作,都会异常
	# 比较运算
		<#if user == "Big Joe">
		  It is Big Joe
		</#if>
		<#if user != "Big Joe">
		  It is not Big Joe
		</#if>
		
		* = 或 != 两边的表达式的结果都必须是标量,而且两个标量都必须是相同类型 (也就是说字符串只能和字符串来比较,数字只能和数字来比较等)
		
		* 对数字和日期类型的比较，也可以使用 <, <=,>= 和 >
	
	# 逻辑操作 || ,&&,!

		<#if x < 12 && color == "green">
		  We have less than 12 things, and they are green.
		</#if>
		<#if !hot> <#-- here hot must be a boolean -->
		  It's not hot.
		</#if>

--------------------
内建函数			|
--------------------
	# 内置函数的调用
		${变量?执行方法}
		${变量?执行方法(方法参数)}
		${变量?执行方法1(方法参数)?执行方法2}

		${testString?upper_case}
		${testString?html}
		${testString?upper_case?html}

		${testSequence?size}
		${testSequence?join(", ")}
	
	# 内建函数的左侧可以是任意的表达式,而不仅仅是变量名
		${testSeqence[1]?cap_first}
		${"horse"?cap_first}
		${(testString + " & Duck")?html}	
	
--------------------
方法调用			|
--------------------
	# 自定义的方法调用
		${repeat("Foo", 3)}
	
	# 方法调用也是普通表达式,和其它都是一样的
		${repeat(repeat("x", 2), 3) + repeat("Foo", 4)?upper_case}

--------------------
不存在的值处理		|
--------------------

	# 默认值操作符
		unsafe_expr!default_expr					${name!"默认值"}
		unsafe_expr! or (unsafe_expr)!default_expr	${name!}	${(name)!"默认值"}
		(unsafe_expr)!								${(name)!}
	
	# 默认值可以是任何类型的表达式,也可以不必是字符串
		hits!0 
		colors!["red", "green", "blue"]
		cargo.weight!(item.weight * itemCount + 10)
	
	
	# 空序列或空哈希表,如果想让默认值为 0 或 false,则不能省略默认值
	
	# 于非顶层变量时,默认值操作符可以有两种使用方式
		${product.color!"red"}		
			* 如果 color 属性不存在,默认返回 red
			* 如果 product 属性不存在,模板异常
		
		${(product.color)!"red"}
			* 如果 product 属性不存在或者 color 属性不存在,都会返回 red
		
		
		* 如果没有括号,仅仅允许表达式最后的一个属性可以未被定义
		

	# 不存在值检测操作符:unsafe_expr?? 或 (unsafe_expr)??
		<#if mouse??>
		  Mouse found
		<#else>
		  No mouse found
		</#if>
		Creating mouse...
		<#assign mouse = "Jerry">
		<#if mouse??>
		  Mouse found
		<#else>
		  No mouse found
		</#if>
	
	# 默认值操作也可以作用于序列子变量
		<#assign seq = ['a', 'b']>
		${seq[0]!'-'}
		${seq[1]!'-'}
		${seq[2]!'-'}
		${seq[3]!'-'}
				
		a
		b
		-			//角标不存在,输出--
		-
	
		* 如果序列索引是负数(比如 seq[-1]!'-') 也会发生错误,不能使用该运算符或者其它运算符去压制它


--------------------
赋值操作符			|
--------------------
	# 并不是表达式,只是复制指令语法的一部分,比如 assign, local 和 global
		<#assign x += y> 是 <#assign x = x + y> 的简写
		<#assign x *= y> 是 <#assign x = x * y> 的简写
		<#assign x--> 是 <#assign x -= 1> 的简写
	
		<#assign x++> 和 <#assign x += 1>不同,它只做算术加法运算 (如果变量不是数字的话就会失败)
	
--------------------
括号				|
--------------------
	//TODO

--------------------
表达式中的空格		|
--------------------
	# FTL 忽略表达式中的多余的 空格。下面的表示是相同的：

--------------------
特殊变量			|
--------------------