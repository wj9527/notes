--------------------------------
python-pip						|
--------------------------------
	* python2 没有自带
	* python3 自带(pip3)
	* pip脚本所在的目录
		C:\Users\Kevin\AppData\Local\Programs\Python\Python36-32\Scripts

	* 执行命令
		pip3 install [模块名称]

	* 参数
	* 模块下载目录(sys.path 中可见)
		C:\Users\Kevin\AppData\Local\Programs\Python\Python36-32\lib\site-packages
		

--------------------------------
python 3.6.5后pip丢失的问题		|
--------------------------------
	* 在该版本的环境下执行: pip,提示不是命名
	* 解决方案,在控制台执行命令
		python -m ensurepip
		
		* 该命令执行成功后会在 script 目录生成pip3等文件
		* 使用 pip3 代替原来的pip