-----------------------------
Poste						 |
-----------------------------
	# 官网
		https://poste.io
	
	# 参考文档
		https://www.wumvp.com/2019/03/24/poste.html
	
	# 域名解析(springcloud.io)

mail		A		[邮件服务器ip]

smtp		CNAME	mail.springcloud.io	 
pop			CNAME	mail.springcloud.io	 
imap		CNAME	mail.springcloud.io

@			MX		mail.springcloud.io
@			TXT		"v=spf1 mx ~all"

s20191223631._domainkey TXT "DKIM key
_dmarc.springcloud.io	TXT "v=DMARC1; p=none; rua=mailto:dmarc-reports@springcloud.io"




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
	
	# 设置admin邮箱 & 密码
		admin@springcloud.io
	
	# 创建密钥
		Virtual domains -> springcloud.io -> create new key
		
		* 把 DKIM key 解析到域名

	# SSL设置
		System settings -> TLS Certificate -> Let's Encrypt certificate

		Enable: √
		Common name: mail.springcloud.io
		Alternativenames: 
			smtp.springcloud.io
			pop.springcloud.io
			imap.springcloud.io


		* 重启docker服务
			docker restart PosteServer
	
	
	# 日志
		* 查看日志
			docker logs --tail=100 mailserver
		
		* 日志目录
			data/logs
