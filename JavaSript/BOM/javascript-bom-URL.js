------------------
URL					|
------------------
	createObjectURL()
		* 参数是二进制图片信息/图片对象,返回的是图片的BASE64编码

------------------
URL	- Demo			|
------------------
	# WEB显示本地图片
		var url = window.URL.createObjectURL(files[0]);		//在内存中根据文本创建了一个二进制对象
		$('#img').attr({'src':url});						//直接把这个二进制对象显示到img,要注意在显示之后,释放掉内存
		window.URL.revokeObjectURL(url);					//释放内存
	