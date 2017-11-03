----------------------------
urllib						|
----------------------------
	* 它是一个包,具有模块
		request
		parse
	* 一系列用于操作URL的功能
	* 可以非常方便地抓取URL内容

----------------------------
urllib-属性					|
----------------------------

----------------------------
urllib-函数					|
----------------------------

----------------------------
urllib-request				|
----------------------------
	* 用于发起HTTP请求的模块
	* 方法
		http.client.HTTPResponse request.urlopen(url, data=None, timeout=socket._GLOBAL_DEFAULT_TIMEOUT,*, cafile=None, capath=None, cadefault=False, context=None)
			* 打开连接,返回实例对象(http.client.HTTPResponse)
			* 参数是可以一个目标 URL
			* 或者是一个 Request 对象
			* url: 可以是一个字符串类型的URL,或者是 request.Request 对象
			* 关键字参数:
				data		# 请求体,字节类型
				timeout		# 超时时间
				

----------------------------
urllib-HTTPResponse			|
----------------------------
	* Http响应对象
	* 实例属性
		status
			* HTTP状态码
		reason
			* 信息

	* 方法
		bytes read()
			* 读取响应体

		bytes readline()
			* 读取一行数据

		int getcode()
			* 返回HTTP响应码

		str geturl()
			* 返回请求的URL

		list readlines()
			* 读取所有行数据

		list getheaders()
			* 获取响应头
			* 是个 tuple 的 list,第一个参数是头名,第二个参数是值
				[('Server', 'nginx/1.13.0'), ('Date', 'Fri, 14 Jul 2017 04:09:01 GMT'), ('Content-Type', 'text/html'), ('Content-Length', '306'), ('Connection', 'close'), ('Accept-Ranges', 'bytes'), ('ETag', 'W/"306-1499759074000"'), ('Last-Modified', 'Tue, 11 Jul 2017 07:44:34 GMT')]

		None close()
			* 关闭资源

----------------------------
urllib-request.Request		|
----------------------------
	* 用于模拟浏览器发起HTTP请求
	* 创建实例对象
		request.Request(url)
			* 通过目标地址,url 创建请求实例对象
	
	* 方法
		add_header(k,v)
			* 添加请求头

----------------------------
urllib-parse				|
----------------------------
	* 可以把参数解析为URL编码的模块
	* 方法
		str urlencode(query, doseq, safe, encoding, errors, quote_via)
			* 把参数解析为URL编码的字符串
			* query参数可以是[(k,v)],也可以是{k:v}
		
		[(,)] parse_qs(qs, keep_blank_values, strict_parsing, encoding, errors)
			* 把URL编码的字符解析为[],列表中的每个元素都是一个 tuple
			* 就是把key value 请求体转换为[(key,value)]
	* demo
		parse.urlencode([
			('username', 'Kevin'),
			('password', 'Keivn'),
			('entry', 'mweibo'),
			('client_id', ''),
			('savestate', '1'),
			('ec', ''),
			('pagerefer', 'https://passport.weibo.cn/signin/welcome?entry=mweibo&r=http%3A%2F%2Fm.weibo.cn%2F')
		])

			
		
----------------------------
urllib-demo					|
----------------------------
# 简单的GET请求
from urllib import request
with request.urlopen('http://springboot.io') as response:
    
    # 响应码
    response_code = response.status
    
    # 异常原因
    response_reason = response.reason
    
    # 响应头集合
    response_headers = response.getheaders()
    
    # 响应体,字节
    response_body = response.read()


# 带参数的GET请求
from urllib import request
client = request.Request("http://springboot.io")
# 添加请求头
client.add_header("Origin", "http://springboot.io")

with request.urlopen(client) as response:
    # 响应码
    response_code = response.status
    
    # 异常原因
    response_reason = response.reason
    
    # 响应头集合
    response_headers = response.getheaders()
    
    # 响应体,字节
    response_body = response.read()


# POST请求
from urllib import request,parse

client = request.Request("http://springboot.io")
# 添加请求头
client.add_header("Origin", "http://springboot.io")

# 请求体,可以是JSON字符串
data = parse.urlencode([
    ('key','value')
])

with request.urlopen(client,data=data.encode(encoding='utf_8', errors='strict')) as response:
    # 响应码
    response_code = response.status
    
    # 异常原因
    response_reason = response.reason
    
    # 响应头集合
    response_headers = response.getheaders()
    
    # 响应体,字节
    response_body = response.read()