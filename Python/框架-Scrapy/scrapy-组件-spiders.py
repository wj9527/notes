-----------------------------
spiders						 |
-----------------------------
	* spider 应该出现在 spiders python目录下
	* 系统预定义代码模版
		import scrapy
		class ItcastSpider(scrapy.Spider):
			name = 'itcast'

			allowed_domains = ['itcast.cn']
			start_urls = ['http://itcast.cn/']

			def parse(self, response):
				pass
	
