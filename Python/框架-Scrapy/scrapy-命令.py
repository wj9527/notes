----------------------------
命令						|
----------------------------
	scrapy startproject [name]
		* 创建一个scrapy爬虫项目
		* 创建好的目录结构
			name
				|-name
					|-spiders
						|-__init__.,py
					|-__init__.,py
					|-items.py
					|-middlewares.py
					|-pipelines.py
					|-settings.py
				|-scrapy.cfg
	
	
	scrapy genspider [name] [url]
		* 创建一个指定name的爬虫,目标地址是url
	
	scarpy crawl [name]
		* 开始启动指定名称的爬虫
		* 参数
			-o
				* 把spider的parse方法返回的数据序列化到本地
				* -o指定文件的名称,会生成在当前命令目录
				* 可以是 .json /.csv /.xml

	

			


	
