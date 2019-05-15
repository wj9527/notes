-------------------------
String					 |
-------------------------
	# 字符串模板
		* 类似于 ES6 的模版
			var name = "KevinBlandy";
			var age = 25;
			println("$name's age is $age")
		
		* 使用 $ 访问变量,如果变量不存在,无法访问,会抛出异常
		* 如果需要输出 $ 符号, 那么需要使用转义字符: "\$"
	
		* 字符串的模板,允许使用简单的表达式: ${表达式}
			var age = 25;
			println("${if (age > 25) "老男人" else "年轻人"}");

			* 在表达式中, 允许合理的嵌套双引号的字符串表达式
		
		* 支持对象的属性导航,需要使用 {} 包括
			var user = User("KevinBlandy", 25);
			println("${user.name},${user.age}");
	

		* 编译后的代码,其实是创建了一个 StringBuilder 对象