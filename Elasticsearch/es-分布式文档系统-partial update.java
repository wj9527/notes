----------------------------
partial update				|
----------------------------
	# 部分替换
	# 创建文档 & 替换文档,可以使用一样的语法
		PUT /index/type/id
	
	# partial update
	
		POST /index/type/id/_update
		{
			"doc":{
				"仅仅需要修改的数据,不需要全量的数据"
			}
		}
		* 看起来比较方便,仅仅需要传递修改修改的参数即可
		* '不需要先读取数据,再修改,直接提交需要修改的字段即可'
		
	# 内部原理
		* 其实es对 partial update 的执行,其实跟全量替换几乎是一样的
		* 在执行 partial update 的时候,内部还是会偷偷的先查询出所有document的数据,然后'更新需要更新的字段'
		* 更新完成后,把旧的document标记为delete,再写入新的doucment
	
	# 优点
		1,所有查询,修改,回写操作,都发生在一个shard内部,避免了所有网络数据传输的开销
			* 读取开销,回写开销
		2,减少了修改和查询中的网络间隔,可以有效减少冲突的情况
			* 当前用户在修改界面,占用时间过长,其实该document已经被其他的用户发生了修改,当前用户执行更新会发生冲突
	

----------------------------
partial update 乐观锁并发控制|
----------------------------
	# 默认会使用乐观锁的并发控制策略
		* partial update 提交到shard后,会先去内容读取该document的所有field,以及version
		* 修改partial update提交的部分field,然后回写,在回写的时候,使用version来处理并发控制

	# retry策略
		* 在执行修改时,发现version不对
		* 再一次读取documnet的最新版本号
		* 基于最新的版本号去更新document
		* 如果失败,则重复上述俩步骤,重复的次数可以通过 retry_on_conflict 值来控制

		POST /user/coder/1/_update?retry_on_conflict=5
	
	# 也可以手动使用 _version 来手动控制,当version不一致时,会给出异常

		