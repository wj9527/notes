------------------------
负载均衡				|
------------------------
	# Consumer均衡算法
		* 当一个group中,有consumer加入或者离开(宕机,关闭)时,会触发partitions负载均衡
		* 负载均衡的最终目的,是提升topic的并发消费能力

			1 假如topic1,具有如下partitions: P0,P1,P2,P3
			2 加入group中,有如下consumer: C0,C1
			3 首先根据partition索引号对partitions排序: P0,P1,P2,P3
			4 根据consumer.id排序: C0,C1
			5 计算倍数:(partitions的数量除以consumer的数量)
				M = [P0,P1,P2,P3].size / [C0,C1].size,本例值 M = 2(向上取整)

			6 然后依次分配partitions: 
				C0 = [P0,P1]
				C1 = [P2,P3]
				
				Ci = [P(i * M),P((i + 1) * M -1)]
				
				i = 0
				m = 2
				P(i * m) = P0
				P((i + 1) * m - 1) = P1
