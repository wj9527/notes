----------------------------
scrapy-入门					|
----------------------------
	* 学习地址
		http://www.cnblogs.com/-E6-/p/7211025.html


----------------------------
scrapy-安装					|
----------------------------
	* pip 安装
		pip install scrapy

	* 处理 building 'twisted.test.raiser' extension
		error: Microsoft Visual C++ 14.0 is required. Get it with "Microsoft Visual	C++ Build Tools": http://landinghub.visualstudio.com/visual-cpp-build-tools
		
		1,从网站下载whl文件
			https://www.lfd.uci.edu/~gohlke/pythonlibs/#twisted

			Twisted
				Twisted-17.9.0-cp36-cp36m-win_amd64.whl
				* cp36		表示python版本
				* win_amd64	64位处理器系统
		
		2,执行安装whl
			pip install Twisted-17.9.0-cp36-cp36m-win_amd64.whl
		
		3,再次执行安装scrapy
			pip install  scrapy


	