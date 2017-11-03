---------------------------
XMLHttpRequest				|
---------------------------
	# 创建
		function createXMLHttpRequest(){
				try{
					//大多数浏览器
					return new XMLHttpRequest();
				}catch(e){
					try{
						return new ActiveXObject("Msxml2.XMLHTTP");//IE6.0
					}catch(e){
						try{
							return new ActiveXObject("Microsoft.XMLHTTP");//IE5.5以及更早版本
						}catch(e){
							alert("为了更好的体验,请立即更换浏览器访问本站");
							throw e;
						}
					}
				}
			}
		
	# 实例方法
		open(method,url,async)
			* 打开一个连接,
			* 第一个参数,以字符串的形式指定请求方式
			* 第二个参数,是字符串,URL,
			* 第三个参数boolean,是否为异步(默认true)
		setRequestHeader(header,value)
			* 设置请求头,第一个参数是 请求头的key,第二个参数是请求头的value
		send()
			* 设置请求体,如果是GET请求,则该方法应该设置为null(解决浏览器兼容问题)
			* 参数可以是URL编码后的字符串,也可以是 FormData 对象
		
		getResponseHeader(name)
			* 获取指定名称的响应头

		getAllResponseHeaders()
			* 获取所有的响应头
		

	# 实例属性
		readyState
			* 对象状态码
				> 0状态:刚创建,还没有调用oppen();方法
				> 1状态:请求开始,调用了oppen();方法,但还没调用send方法
				> 2状态:请求发送完成,调用完了send方法
				> 3状态:服务器已经开始响应,但不表示响应结束了
				> 4状态:服务器响应结束(通常我们只关心这个状态...)
		status
			* HTTP响应码
		
		timeout
			* 超时时间,单位(ms)
			* 默认 120 s
		
		responseTyper	
			* 声明服务端响应的数据类型
			* 可选值
				arraybuffer,
				blog,	
				

		responseXML
			* 返回服务器响应的XML数据

		responseText
			* 返回服务器响应的TEXT数据

		withCredentials
			* 在跨域请求的时候,是否把Cookie值也发送到跨域服务器
		
		upload
			* 当提交的表单是文件表单的时候,该属性会存在
			* 该属性可以监听一个上传事件:progress
			* Event属性
					total;		//获取上传文件的总(所有)大小
					loaded;		//获取已经上传的文件大小
	
	# 事件
		onreadyStatechange
			* 当异步对象的状态发生改变的时候调用
			* 当使用 async=false 时，不用编写 onreadystatechange 函数,把代码放到 send() 语句后面即可

		
---------------------------
XMLHttpRequest-GET			|
---------------------------
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.open("GET", "/test", true);
	xmlHttp.send(null);//GET请求没有请求体,但是也要给出null,不然FireFox可能无法发送
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			//获取服务端的响应
			var text = xmlHttp.responseText;
		}
	}

---------------------------
XMLHttpRequest-POST			|
---------------------------
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.open("POST", "/test", true);		
	//当请求为POST的时候,需要手动添加请头
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");	
	//请求体
	xmlHttp.send("userName=kevin&passWord=123456");				
	xmlHttp.onreadystatechange = function()	{					
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){	
			//获取服务端的响应
			var text = xmlHttp.responseText;
		}
	}

---------------------------
XMLHttpRequest-二进制		|
---------------------------
		var oReq = new XMLHttpRequest();
		oReq.open("GET", "/common/get/captcha", true);
		//响应体为二进制的blob
		oReq.responseType = "blob";
		oReq.onreadystatechange = function () {
				if (oReq.readyState == oReq.DONE) {
				//获取二进制数据
				var blob = oReq.response;
				var obj = {};
				obj.imgSrc = URL.createObjectURL(blob);
				console.log(obj.imgSrc);
			}
		}
		oReq.send(); 