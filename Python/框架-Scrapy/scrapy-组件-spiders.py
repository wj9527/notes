-----------------------------
spiders						 |
-----------------------------
	* spider 应该出现在 spiders python目录下
	* spider的parse要么返回 item,要么返回 Request,意思是要么处理数据,要么继续爬

-----------------------------
spiders	定义				 |
-----------------------------
	import scrapy
	class ItcastSpider(scrapy.Spider):

		# 指定爬虫的名称
		name = 'itcast'

		# 允许爬取的域
		allowed_domains = ['itcast.cn']

		# 开始的地址
		start_urls = ['http://itcast.cn/']

		# 爬取结果处理(self可以访问上面的所有属性,以及自定义的属性)
		def parse(self, response):	
			pass
		
		* name,allowed_domains 等属性,都是当前Spider实例对象(可以通过 self 访问)
		
-----------------------------
spiders	重新发起请求		 |
-----------------------------
	* 在爬取过程中,如果从响应中爬取出了连接,需要深度爬取,则返回 scrapy.Request() 实例对象

		def parse(self, response):	
			...
			# 解析出数据,交给pipe处理
			yield item

			...
			# 解析出url,重新发送给调度器,入队列,并且指定回调函数,就是当前函数
			yield scrapy.Request('http://....com',callback=self.parse)
	
	* scrapy.Request(url, callback=None, method='GET', headers=None, body=None,
                 cookies=None, meta=None, encoding='utf-8', priority=0,
                 dont_filter=False, errback=None, flags=None)
		* 第一个参数,就是提取出来的url
		* 关键字参数
			callback
				* 该连响应后,由谁进行处理(回调函数)
