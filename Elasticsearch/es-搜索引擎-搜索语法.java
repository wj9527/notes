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


------------------------------------
Query String 基础语法				|
------------------------------------
	GET /index/type/_search?q=content:elaticsearch
		* content必须包含elaticsearch关键字	
		
	GET /index/type/_search?q=+content:elaticsearch
		*  同上
		
	GET /index/type/_search?q=-content:elaticsearch
		* content必须没包含elaticsearch关键字
	
	GET /_search
	GET /_index1,index2/type1,type2/_search
		* 针对于多个index,type的检索
	
	
	* HTTP协议规定GET没有请求体,一般也不允许GET请求带有body,但GET更加适合于检索场景
	* 如果遇到不支持的场景,也可以使用:POST /_search


------------------------------------
Query String - 分页	& 排序			|
------------------------------------
	* 分页
		GET /_search?from=0&size=10

		* from 表示从第几条数据开始
		* size 表示取几条数据
	
	* 排序
		GET /_search?sort=age:asc

		* 按照age升序排序


------------------------------------
DSL	- 基础操作						|
------------------------------------
	# 分页
		{
			"from":0,
			"size":10
		}
	
	# 排序
		{
			"from":1,
			"size":2,
		}

	# 仅仅检索指定的字段
		{
			"_source":["name","id","skill.java"]	//仅仅检索指定的字段
		}
		* 仅仅检索Documnet里面的name,id属性.以及skill属性(对象)里面的java属性

------------------------------------
DSL	- query							|
------------------------------------

	# 检索所有
		{
			"query":{
				"match_all": {}    
			}
		}
	
	# 根据字段数据检索
		{
			"query":{
				"match":{
					"name":"KevinBlandy"
				}
			}
		}
		* 检索 name 属性里面包含了 KevinBlandy 的记录
		* 一个match只能有一个属性,不支持多个属性
	
	# boolean 属性
		* boolean 属性是一个或者多个条件
		* 属性名称枚举固定,属性值可以为数组或者对象
			* must		一个或者多个条件,必须全部满足
			* should	一个或者多个条件,满足一个即可
			* must_not	一个或者多个条件,必须全部不满足
		
	
	#  组合多个条件
		{
			"query":{
				"bool":{
					"must":{
						"match":{
							"name":"KevinBlandy"
						}
					},
					"should":[
						{
							"match":{
								"name":"KevinBlandy"
							}
						},
						{
							"bool":{
								"must":{
									"match":{
										"name":"KevinBlandy"
									}
								},
								"must_not":{
									"match":{
										"name":"KevinBlandy"
									}
								}
							}
						}
					],
					"must_not":[
						{
							"match":{
								"name":"Litch"
							}
						}
					],
					"minimm_should_match":1
				}
			}
		}
		
		* must	必须
		* should 任何一个条件满足即可
		* minimm_should_match 至少要匹配到一条
			






















