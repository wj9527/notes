-----------------------------
Poste						 |
-----------------------------
	# 官网
		https://poste.io
	
	# 域名解析

mail.springcloud.io		A		[邮件服务器ip]
springcloud.io			MX		mail.springcloud.io. 10
springcloud.io			IN TXT	"v=spf1 mx ~all"

[邮件服务器ip]				PTR		mail.springcloud.io
	* 这没法解析


	# Docker 安装
docker run \
-p 25:25 \
-p 80:80 \
-p 110:110 \
-p 143:143 \
-p 443:443 \
-p 587:587 \
-p 993:993 \
-p 995:995 \
-v /etc/localtime:/etc/localtime:ro \
-v /srv/poste/data:/data \
--name "PosteServer" \
-h "mail.springcloud.io" \
-t analogic/poste.io
	
	# 设置admin邮箱
		admin@springcloud.io

	# SSL设置
		System settings -> TLS Certificate -> Let's Encrypt certificate
	
