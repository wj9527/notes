------------------------
items					|
------------------------
	* 其实就是定义model
	* 简单的model定义
		import scrapy
		class WebspiderItem(scrapy.Item):
			name = scrapy.Field()		# 定义一个name属性
			age = scrapy.Field()		# 定义一个age属性
			gender = scrapy.Field()		# 定义一个gender属性
	

			