-------------------------
Collections				 |
-------------------------
	# 用于操作 Set,List,Map 的静态工具类


-------------------------
Collections-静态字段/方法|
-------------------------
	boolean addAll(Collection<? super T> c, T... elements)
		* 把变长数据都存入c
	
	void shuffle(List<?> list);
		* 随机打乱集合数据
	
	void shuffle(List<?> list, Random rnd)
		* 同上,指定的 Random
	
