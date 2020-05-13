------------------------------
聚合检索
------------------------------
	# 管道和步骤
		* 聚合的过程称为管道 pipeline
		* 管道由多个步骤 stage 组成
		* 每个stage对文档进行计算, 并且把计算后的结果给下一个stage
	
	
		const pipeline = [$stage1, $stage2 .....]
		db.[collection].aggregate(pipeline, {
			...options
		});
	

	# 指令
		$match
			* 过滤
		$project
			* 投影
		$sort
			* 排序
		$group
			* 分组
		$skip
			* offset
		$limit
			* 结果限制
		$lookup
			* 左外链接

