------------------------------------
Query String 基础语法				|
------------------------------------
	GET /index/type/_search?q=content:elaticsearch
		* content必须包含elaticsearch关键字	
		
	GET /index/type/_search?q=+content:elaticsearch
		*  同上
		
	GET /index/type/_search?q=-content:elaticsearch
		* content必须没包含elaticsearch关键字
		
------------------------------------
_all metadata的原理和作用			|
------------------------------------
	GET /_search?q=elaticsearch
		* 所有的index,所有的type,所有的document,所有的field里面有elaticsearch就符合条件
		* 这个在生产环境用得最多
		* 也可以通过url去限制index,type
	
	# 原理
		* 在建立索引的时候,插入一条document,它包含了多个field,此时es会把多个field的值用字符串串联,变成一个很长的字符串
			{
				"name":"KevinBlandy",
				"age":23,
				"gender":"男"
			}
			=============== 元数据
			"KevinBlandy 23 男"

		* 这个字符串叫做 _all 元数据

	
