------------------------------
Shell						  |
------------------------------
	# 启动shell并且连接到远程的服务器
		mongo [host]:[port]/[db]

		* 如果 db 不存在,会创建
	
		--nodb
			* 启动时,不连接任何的mongodb
		--norc
			* 启动时不执行 .mongorc.js 脚本
	
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
	
	# 在脚本里面可以访问 db 变量,和其他的全局变量,但是Shell的一些辅助函数,在js文件里面不能直接使用,但是可以用函数来代替
		// 切换DB,记得要用db变量重新接收赋值
		db.getSisterDB(db);					// use db
			db = db.getSisterDB(db);

		// 查看DBS
		db.getMongo().getDBs();				// show dbs
			{
				"databases" : [{
						"name" : "admin",
						"sizeOnDisk" : 32768,
						"empty" : false
					}
				}],
				"totalSize" : 241664,
				"ok" : 1
			}

		// 查看collections
		db.getCollectionNames();			// show collections
			[ "c1", "c2", "c3" ]
		

------------------------------
Shell 全局指令/函数			  |
------------------------------	
	help
		* 查看帮助
	cls
		* 清空面板
	exit
		* 退出shell
	print()
		* 打印函数
	load(file)
		* 加载指定的js脚本,并且执行
		* 默认加载当前目录的脚本,也可以使用绝对路径加载其他文件夹下的脚本
	run(shell)
		* 执行shell命令
		* 执行成功返回 0
	
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
			db.getCollectionNames();	// show collections
	DB
		# db对象

	DBCollection
		# 集合对象

------------------------------
.mongorc.js					  |
------------------------------
	# 该js文件在用户的home目录下: ~/.mongorc.js 
	# 在启动 mongo 客户端的时候,会自动的加载执行
		mongo
	
	# 可以利用它来完成一些初始化功能(设置全局变量啥的)

	# 重写危险的函数,防止手误触发
		function no(args){
			print('危险函数,不能执行');
		}

		// 禁止删除数据库
		db.dropDatabase = DB.prototype.dropDatabase = no;

		// 禁止删除集合
		DBCollection.prototype.drop = no;

		// 禁止删除索引
		DBCollection.prototype.dropIndex = no;

