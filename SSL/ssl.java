-----------------------------
java ssl					 |
-----------------------------
	# keytool 指令一览
		 -certreq            生成证书请求
		 -changealias        更改条目的别名
		 -delete             删除条目
		 -exportcert         导出证书
		 -genkeypair         生成密钥对
		 -genseckey          生成密钥
		 -gencert            根据证书请求生成证书
		 -importcert         导入证书或证书链
		 -importpass         导入口令
		 -importkeystore     从其他密钥库导入一个或
		 -keypasswd          更改条目的密钥口令
		 -list               列出密钥库中的条目
		 -printcert          打印证书内容
		 -printcertreq       打印证书请求的内容
		 -printcrl           打印 CRL 文件的内容
		 -storepasswd        更改密钥库的存储口令
	
	# 生成证书到keystore
		keytool -genkey -deststoretype [pkcs12] -alias [alias] -validity [100] -keystore [server.keystore] -keyalg [RSA] -storepass [密码]
			-genkey
				* 生成证书的指令
			-deststoretype	
				* 指定证书类型,一般固定值:pkcs12
			-alias
				* 指定证书在keystore中的别名
			-validity
				* 有效期,单位是天
			-keystore
				* 指定 keystore 名称(如果keystore不存在,会新建)
			-keyalg
				* 指定证书的非对称加密算法,一般固定:RSA
			-storepass
				* keystore的密码
	
	# 从证书中导出公钥
		keytool -export -alias [alias] -file [name.cer] -keystore [name.keystore] -storepass [密码]

			-export
				* 导出公钥的指令
			-alias
				* keystore 中证书的别名
			-file
				* 公钥证书的文件名称
			-keystore
				* keystore
			-storepass
				* keystored的密码
	
	# 导入公钥到 keystore
		keytool -import -file [name.cer] -alias [alias] -keystore [name.keystore] -storepass [密码]

			-import
				*  导入指令
			-file
				* 公钥证书文件
			-alias
				* 设置 该证书在目标keystore中的别名(默认名:mykey),不能冲突
			-keystore
				* 导入到的目标 keystore(如果keystore不存在,会新建)
			-storepass
				* 目标 keystore的密码
			
	
	# 从keystore删除公钥
		keytool -delete -alias [alias] -keystore [name.keystore] -storepass [密码]
			-delete
				* 删除指令
			-alias
				* 公钥的别名
			-keystore
				* 目标keystore文件
			-storepass
				* keystore的密码
				
	# 查看keystore中的证书条目
		keytool -list -v -keystore [name.keystore] -storepass [密码]

			-list
				* 列出指令
			-v
				* 显示详情
			-keystore
				* 指定的keystore
			-storepass
				* keystore的密码
	
	# 生成证书请求
		keytool -certreq -alias [alias] -file [name.csr] -keystore [name.keystore] -storepass [密码]
		
			-certreq
				* 生成请求指令
			-alias
				* 证书在keystore中的别名
			-file
				* 生成的请求文件名称
			-keystore
				* 证书所在的keystore
			-storepass
				* keystore的密码
			
	
	# 根据证书请求生成证书
		keytool -gencert -alias [alias] -infile [name.csr] -outfile [name.cer] -keystore [name.keystore] -storepass [密码]
			
			-gencert
				* 生成请求证书指令
			-alias
				* 用于签发的证书的证书别名(root证书别名)
			-infile
				* 请求文件
			-outfile
				* 生成的证书名称
			-keystore
				* 用于签发的证书的证书所在的keystore(root证书)
			-storepass
				* 用于签发的证书的证书所在的keystore的密码(root证书)
	
	# 打印证书
		keytool -printcert -rfc -file [name.cer]

			-printcert 
				* 打印指令
			-rfc 
			-file 
				* 证书文件

------------------------------------------
keytool制作CA根证书以及颁发二级证书		  |
------------------------------------------
	# 过程
		1,生成CA证书
			keytool -genkey
		
		2,导出CA证书公钥
			keytool -export

		3,客户端生成证书
			keytool -genkey
		
		4,客户端生成请求文件
			keytool -certreq

		5,使用CA证书进行签发
			keytool -gencert
		
		6,客户端导入CA证书
			keytool -import

		7,客户端获取到CA签发的证书,导入自己的库
			keytool -import


	# 先成根证书
		keytool -genkey -deststoretype pkcs12 -alias rootca -keystore rootca.keystore -keyalg RSA -storepass 123456
		
		keytool -export -alias rootca -file root.cer -keystore rootca.keystore -storepass 123456

	# 生成自签名证书,并且生成请求文件
		keytool -genkey -deststoretype pkcs12 -alias app1 -keystore app1.keystore -keyalg RSA -storepass 123456
	
		keytool -certreq -alias app1 -file app1.csr -keystore app1.keystore -storepass 123456
	
	# 使用根证书进行认证
		* 认证证书

			keytool -gencert -alias rootca -infile app1.csr -outfile app1.cer -keystore rootca.keystore -storepass 123456
		
		* 导入CA证书
			keytool -import -file root.cer -alias root -keystore app1.keystore -storepass 123456
		
		* 导入签发的证书
			keytool -import -file app1.cer -alias app1 -keystore app1.keystore -storepass 123456
		
		*  查看kestore里的证书列表
			keytool -list -v -keystore app1.keystore -storepass 123456