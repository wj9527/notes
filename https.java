https://www.oschina.net/news/94188/acme-v2-and-wildcard-certificate-support-is-live
https://my.oschina.net/kimver/blog/1634575
https://github.com/Neilpang/acme.sh/wiki/%E8%AF%B4%E6%98%8E

## Nginx反向代理http的tomcat
	1,执行
		curl https://get.acme.sh | sh

	2,执行
		source ~/.bashrc

	3,执行
		# 阿里云后台的密钥
		export Ali_Key="1858118"
		export Ali_Secret="1858118"

		# 填写自己的域名
		acme.sh --issue --dns dns_ali -d springboot.io -d *.springboot.io
		
		* acme.sh比certbot的方式更加自动化,省去了手动去域名后台改DNS记录的步骤,而且不用依赖Python
		* 第一次成功之后,acme.sh会记录下App_Key跟App_Secret,并且生成一个定时任务,每天凌晨0：00自动检测过期域名并且自动续期
		* 对这种方式有顾虑的,请慎重,不过也可以自行删掉用户级的定时任务,并且清理掉~/.acme.sh文件夹就行
	
	4,在证书生成目录执行
		acme.sh --installcert -d springboot.io -d *.springboot.io  \
		--keypath       /usr/local/ssl/springboot/springboot.io.key  \
		--fullchainpath /usr/local/ssl/springboot/springboot.io.pem
		
		* 会把key和pem生成到指定的目录
	
	5,配置nginx

			server {
					listen 443;
					server_name springboot.io www.springboot.io;

					ssl on;
					ssl_certificate      /usr/local/ssl/springboot/springboot.io.pem;
					ssl_certificate_key  /usr/local/ssl/springboot/springboot.io.key;

					access_log  logs/springboot.io.log  main;
					error_log  logs/springboot.io.error.log;

					proxy_set_header Host $host;
					proxy_set_header X-Forwarded-Host $host;
					proxy_set_header X-Forwarded-Server $host;
					proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
					proxy_set_header X-Requested-For $remote_addr;
					proxy_set_header REMOTE-HOST $remote_addr;

					proxy_http_version 1.1;
					proxy_set_header Upgrade $http_upgrade;
					proxy_set_header Connection "upgrade";

					location / {
							proxy_pass http://127.0.0.1:1024;
							proxy_connect_timeout 600;
							proxy_read_timeout 600;

					}
			}

			server {
				listen       80;
				server_name  springboot.io www.springboot.io;
				return  301 https://springboot.io$request_uri;
			}

## Springboot单独配置
	1,(在证书生成目录)生成keystore
		* 生成 p12 文件(会输入一次密码)
			openssl pkcs12 -export -in fullchain.cer -inkey springboot.io.key -out springboot.io.p12
			
		* 根据p12 文件生成 keystore 文件
			keytool -importkeystore -v  -srckeystore springboot.io.p12 -srcstoretype pkcs12 -srcstorepass [生成p2文件的密码] -destkeystore springboot.io.keystore -deststoretype jks -deststorepass [key.store的密码]
		
			* 如果提示警告,可以考虑根据警告的命令,再执行一波
				keytool -importkeystore -srckeystore springboot.io.keystore -destkeystore springboot.io.keystore -deststoretype pkcs12


	2,springboot配置
		#ssl
		server.ssl.enabled=true
		server.ssl.key-store=classpath:ssl/springboot.io.p12
		server.ssl.key-store-type=PKCS12
		server.ssl.key-store-password=[key.store的密码]


----------------------------------

_add_a_and_b:
   push   %ebx
   mov    %eax, [%esp+8] 
   mov    %ebx, [%esp+12]
   add    %eax, %ebx 
   pop    %ebx 
   ret  

_main:
   push   3
   push   2
   call   _add_a_and_b 
   add    %esp, 8
   ret

