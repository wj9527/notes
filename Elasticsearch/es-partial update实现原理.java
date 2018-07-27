----------------------------
partial update				|
----------------------------
	# 部分替换
	# 创建文档 & 替换文档,就是一样的语法
		PUT /index/type/id
	
	# partial update
	
		POST /index/type/id/_update
		{
			"doc":{
				"仅仅需要修改的数据,不需要全量的数据"
			}
		}

	# 内部原理
		* 其实es对 partial update 的执行,其实跟全量替换几乎是一样的
