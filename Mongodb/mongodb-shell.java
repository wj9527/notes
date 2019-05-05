------------------------------
Shell						  |
------------------------------
	# 启动shell并且连接到远程的服务器
		mongo [host]:[port]/[db]

		* 如果 db 不存在,会创建
	
		--nodb
			* 启动时,不连接任何的mongodb
	
	# 直接'执行函数'名称,可以看到函数的实现源码

		db.createCollection;

		function (name, opt) {
			...
		}
	
	# 直接执行外部的js脚本
		mango [file0.js] [file1.js] [file2.js]

		* shell会依次执行js然后退出
		* 可以使用的指令
			--quiet [host]:[port]/[db] 

				* 在指定的服务器上执行js脚本
	
	# 在脚本里面可以访问 db 变量,和其他的全局变量,但是Shell的一些辅助函数,在js文件里面不能使用
		use db;
		show dbs;
		show collections;

		* 它们可以使用函数来代替

		// 切换DB
		db.getSitsterDB(db);		// use db
		// 查看DBS
		db.getMongo().getDBs();		// show dbs
		// 查看collections
		db.getConnectionNames();	// show collections
		

------------------------------
Shell 全局指令/函数			  |
------------------------------	
	help
		* 查看帮助
	cls
		* 清空面板
	print()
		* 打印函数
	load(file)
		* 加载指定的js脚本,并且执行
	
------------------------------
Shell 内置对象				  |
------------------------------
	Mongo
		# 表示一个服务端连接对象
			// 创建与服务端的连接
			let connection = new Mongo('127.0.0.1:27017');

			// 从连接获取db实例
			let db = connection.getDB('test');
		
			
			// 切换DB
			db.getSitsterDB(db);		// use db
			
			// 查看DBS
			db.getMongo().getDBs();		// show dbs

			// 查看collections
			db.getConnectionNames();	// show collections