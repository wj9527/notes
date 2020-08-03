------------------------
Nginx-�������			|
------------------------
	1,��Nginx�İ�װĿ¼��:confĿ¼��
	2,�ҵ�:nginx.conf�ļ�

------------------------
Nginx-��������			|
------------------------

	server {
		//�����˿�
		listen 80;

		//��������
		server_name manager.kevinblandy.com;

	//��־�ļ�����
	#access_log  logs/kevinblandy.com.access.log  main;

	//������־�ļ�
	#error_log  logs/kevinblandy.com.error.log;


	//ת���ͻ��������ʱ��Я��������
	proxy_set_header Host $host;

	proxy_set_header X-Forwarded-Host $host;
	proxy_set_header X-Forwarded-Server $host;
	proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

	//ת���ͻ�����ʵIP(ͨ�� request.getHeader("X-Requested-For")��ȡ)
	proxy_set_header X-Requested-For $remote_addr;
	proxy_set_header REMOTE-HOST $remote_addr;

		//ӳ��
		location / {
			//ת����ַ+�˿�
			proxy_pass http://127.0.0.1:8081;
			//���ӳ�ʱ
			proxy_connect_timeout 600;
			//��ȡ��ʱ
			proxy_read_timeout 600;
		}
	}

	# ���JAVA�����л�ȡ�ͻ�����ʵ�ķ���IP
		String ip1 = request.getHeader("X-Requested-For");

	# ���Tomcat ��־��,�ͻ��˵���ʵ����IP
		# �޸�tomcat��־���ã�(<host></host>�м�)

		<Valve className="org.apache.catalina.valves.AccessLogValve"  
			directory="logs"  prefix="tomcat_access_log" suffix=".txt"  
			pattern="%{X-Requested-For}i %l %u %t &quot;%r&quot; %s %b" 
			resolveHosts="false"/>

		* û��Nginx��һ���ʱ��ֱ����%a�Ϳ��Ի�ÿͻ���IP���������ǵ���%{X-Requested-For}i �������ʵ��IP�ˡ�

		* ��ʵֻ��Ҫ�޸�:%h %l %u %t &quot;%r&quot; %s %b ԭʼֵ��OK

		* �滻Ϊ:%{X-Requested-For}i %l %u %t &quot;%r&quot; %s %b


------------------------
Nginx-��̬�ļ�			|
------------------------
	  server{
			listen 80;
			server_name static.kevinblandy.com;
			location / {
				root /usr/local/static;		
				autoindex on;               # ��������    
				charset utf-8,gbk;			# ����ļ������������������
				autoindex_exact_size on;    # ��ʾ�ļ���С        
				autoindex_localtime on;     # ��ʾ����޸�ʱ��     
			}
		}

------------------------
Nginx-WebSocket			|
------------------------
	# �������WebSocket,��Ҫ���һЩ����

	 proxy_http_version 1.1;        
	 proxy_set_header Upgrade $http_upgrade;
	 proxy_set_header Connection "upgrade";

------------------------
Nginx-�����ض���		|
------------------------
	# �� springboot.io �� www.springboot.io �ض��� www.javaweb.io
		server{
				listen 80;
				server_name www.springboot.io springboot.io;
				rewrite ^/(.*)$ http://www.javaweb.io/$1 permanent;  
		}

------------------------
Nginx-https				|
------------------------
	server {
		listen 443 ssl;
		server_name springboot.io www.springboot.io;

		ssl_certificate      /usr/local/ssl/springboot/springboot.pem;
		ssl_certificate_key  /usr/local/ssl/springboot/springboot.key;

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

	# http�ض���https
	server {
		listen       80;
		server_name  springboot.io www.springboot.io;
		return  301 https://springboot.io$request_uri;
	}

------------------------
Nginx-http2����			|
------------------------
	# ��Ҫ����ģ��֧��,���뿪��https
	# ���ĵ�����
		listen 443 ssl http2;;
	
		server{
				listen 443 ssl http2;
				server_name *.c-lang.cn;
				
				ssl_certificate      /etc/letsencrypt/live/c-lang.cn/fullchain.pem;
				ssl_certificate_key  /etc/letsencrypt/live/c-lang.cn/privkey.pem;
				
				proxy_set_header Host $host;
				proxy_set_header X-Forwarded-Host $host;
				proxy_set_header X-Forwarded-Server $host;
				proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
				proxy_set_header X-Requested-For $remote_addr;
				proxy_set_header REMOTE-HOST $remote_addr;
				
				# websocket �Ǳ���
				proxy_http_version 1.1;
				proxy_set_header Upgrade $http_upgrade;
				proxy_set_header Connection "upgrade";
				
				location / {
						root html;
						index index.html index.htm;
				}       
			}