----------------------------
BloomFilter					|
----------------------------
	# 布隆过滤的实现

	# Demo
		/**
        * 创建一个插入对象为一亿，误报率为0.01%的布隆过滤器
        */
		BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),100000000, 0.0001);
		
		//可以添加一亿个元素
		bloomFilter.put("123456");
		bloomFilter.put("123456");
		bloomFilter.put("123456");
		
		//是否可能包含
		boolean result = bloomFilter.mightContain("121");
		System.out.println(result);