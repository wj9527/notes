-----------------------------
spiders						 |
-----------------------------
	* spider 应该出现在 spiders python目录下
	* spider 必须继承自:scrapy.Spider
	* spider的parse要么返回 item,要么返回 Request,意思是要么处理数据,要么继续爬

-----------------------------
spiders-主要的属性和方法	 |
-----------------------------
	name
		* 定义spider名字的字符串
		* 例如,如果spider爬取 mywebsite.com ,该spider通常会被命名为 mywebsite
	allowed_domains
		* 包含了spider允许爬取的域名(domain)的列表,可选
	start_urls
		* 初始URL元祖/列表,当没有制定特定的URL时,spider将从该列表中开始进行爬取

	start_requests(self)
		* 该方法必须返回一个可迭代对象(iterable),该对象包含了spider用于爬取(默认实现是使用 start_urls 的url)的第一个Request
		* 当spider启动爬取并且未指定start_urls时,该方法被调用

	parse(self, response)
		* 当请求url返回网页没有指定回调函数时,默认的Request对象回调函数
		* 用来处理网页返回的response,以及生成Item或者Request对象

	log(self, message[, level, component])
		* 使用 scrapy.log.msg() 方法记录(log)message
		* 更多数据请参见 logging

-----------------------------
基本spiders	定义			 |
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
			response.url				# 爬取时请求的url
			response.body				# 返回的html
			response.body_as_unicode()	# 返回的html unicode编码
		
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

-----------------------------
Selectors选择器				 |
-----------------------------
	* response.selector,将获取到一个 response 初始化的类 Selector 的对象
	* 通过使用 response.selector.xpath()或response.selector.css() 来对 response 进行查询
	* Scrapy也提供了一些快捷方式来进行访问
		response.xpath
		response.css()
	
	* Scrapy Selectors 内置 XPath 和 CSS Selector 表达式机制

	* Selector有四个基本的方法,最常用的还是xpath

		xpath()		传入xpath表达式,返回该表达式所对应的所有节点的selector list列表
		extract()	序列化该节点为Unicode字符串并返回list
		css()		传入CSS表达式,返回该表达式所对应的所有节点的selector list列表,语法同 BeautifulSoup4
		re()		根据传入的正则表达式对数据进行提取,返回Unicode字符串list列表

	
-----------------------------
Spider-派生类-CrawlSpider	 |
-----------------------------
	scrapy.spiders.CrawlSpider
		* Spider类的设计原则是只爬取start_url列表中的网页
		* 而CrawlSpider类定义了一些规则(rule)来提供跟进link的方便的机制
		* 从爬取的网页中获取link并继续爬取的工作更适合

-----------------------------
LinkExtractor				 |
-----------------------------
	* 用于从html结构体中提取出连接
	* from scrapy.linkextractors import LinkExtractor
	* 构造函数
		LinkExtractor(allow=(), deny=(), allow_domains=(), deny_domains=(), restrict_xpaths=(),
                 tags=('a', 'area'), attrs=('href',), canonicalize=False,
                 unique=True, process_value=None, deny_extensions=None, restrict_css=(),
                 strip=True)
			allow
				* 表示匹配的规则,是一个或者多个正则表达式(元组)
				* 如果该值为空,则所有的链接都会被提取
			deny
				* 也是一组正则规则,该规则命中的连接,不会被提取
			allow_domains
				* 会被提取的链接域
			deny_domains
				* 不会被提取的连接域
			restrict_xpaths
				* 使用xpath表达式,与allow共同过滤链接

	* 属性 & 方法
		extract_links(response)
			* 传入响应对象,进行link解析,返回 Link 对象集合
	* demo
		def parse(self, response):
			# 创建 extrator 对象(匹配规则)
			extrator = LinkExtractor(allow=('start=\d+',))
			# 通过 extract_links 来解析 response 对象,获取Link对象集合 
			links = extrator.extract_links(response)
			for i in links:
				print(i)	# Link(url='http://hr.tencent.com/position.php?tid=87&start=10#a', text='2', fragment='', nofollow=False)

-----------------------------
Rule						 |
-----------------------------
	* 用于定义怎么去处理匹配到的连接
	* 一个 Rule 里面会包含一个匹配规则(LinkExtractor),程序里面可以包含多个 Rules
	* 如果多个 Rule 匹配了相同的连接,则根据规则在集合中的定义顺序,第一个将会被使用
	* from scrapy.contrib.spiders import Rule
	* 构造函数
		Rule(link_extractor, callback=None, cb_kwargs=None, follow=None, process_links=None, process_request=identity)
			link_extractor
				* 表示一个匹配规则(LinkExtractor)
			callback
				* 回调函数,每当从link_extractor中获取到连接的时候,都会作为参数传递给该回调函数
			follow
				* bool 值,指定了根据该规则提取出来的连接是否要跟进(打开连接,深度提取)
				* 如果 callback = None,该值默认为 True,否则该值为 False
			process_links
				* 指定spider中哪个函数将会被调用,从匹配规则(LinkExtractor)中获取到链接列表时会调用该函数

	* 属性 & 方法
		
		
	* demo
		
	
