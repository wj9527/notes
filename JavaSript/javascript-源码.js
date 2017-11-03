
----------------------------------------
Array-自定义原型方法,可以遍历多维数组	|
----------------------------------------
	Array.prototype.each = function(fun){
		try{
			//预定义初始化变量
			this.i || (this.i = 0);
			//确定当前数组有元素,并且传递的参数是一个函数
			if(this.length > 0 && fun.constructor == Function){
				while(this.length > this.i){
					//获取每一个元素
					var temp = this[this.i];
					if(temp && temp.constructor == Array){
						//如果当前元素还是一个数组,递归调用
						temp.each(fun);
					}else{
						//如果不是,则执行传递进来的函数
						var obj = true;
						fun.call(temp,temp);
					}
					//释放变量内存
					this.i ++;
				}
				this.i = null;
			}
		}catch(e){
			//TODO
		}
		return this;
	}
	var arr = [5,6,7,8,9,[10,11,12,[55,[66]]]];
	arr.each(function(val){
		console.log(val);
	});

----------------------------------------
Date-获取当前时间						|
----------------------------------------
	function now(){
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;	
		if(month < 10){
			month = "0" + month;
		}
		var dayOfMonth = date.getDate();
		if(dayOfMonth < 10){
			dayOfMonth = "0" + dayOfMonth;
		}
		var hours = date.getHours();		
		if(hours < 10){
			hours = "0" + hours;
		}
		var minutes = date.getMinutes();	
		if(minutes < 10){
			minutes = "0" + minutes;
		}
		var seconds = date.getSeconds();
		if(seconds < 10){
			seconds = "0" + seconds;
		}
		return year + "年" + month + "月" + dayOfMonth + "日 " + hours + ":" + minutes + ":" + seconds;
	}

----------------------------------------
Date-显示本地图片						|
----------------------------------------
	var input = document.getElementsByTagName("input")[0];
	input.onchange = function (e) {
		var url = window.URL.createObjectURL(this.files[0]);
		document.getElementsByTagName("img")[0].src = url;
		//window.URL.revokeObjectURL(url);					
	};

----------------------------------------
location-获取URL后的请求参数			|
----------------------------------------
	function urlArgs(){
		var args = {};
		//获取参数字符串,删除?号
		var query = location.search.substring(1);
		//根据 & 符合分割字符串
		var pairs = query.split("&");
		for(var x = 0;x < pairs.length;x++){
			//查找name=value
			var pos = pairs[x].indexOf("=");
			if(pos == -1){
				continue;
			}
			var name = pairs[x].substring(0,pos);
			var value = pairs[x].substring(pos + 1);
			//URI解码
			value = decodeURIComponent(value);
			if(args.hasOwnProperty(name)){
				//属性已经存在,以','号分隔
				value = args[name] + "," + value;
			}
			args[name] = value;
		}
		return args;
	}

----------------------------------------
doucment-获取所有的Cookie				|
----------------------------------------
	function getCookies (){
		var cookie = {};
		var all = document.cookie;
		if(all === ''){
			return cookie;				
		}
		var list = all.split(';');
		for(var x = 0; x < list.length ; x++){
			var cookieStr = list[x];
			var point = cookieStr.indexOf('=');
			var name = cookieStr.substring(0,point);
			var value = decodeURIComponent(cookieStr.substring(point + 1));
			cookie[name] = value;
		}
		return cookie;
	}