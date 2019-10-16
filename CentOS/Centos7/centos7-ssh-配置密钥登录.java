
# 在客户端生成密钥对
	* 使用ssh工具生成
		ssh-keygen -t rsa
	
	* 使用Git客户端生成
		ssh-keygen -t rsa -C "747692844@qq.com"

# 修改配置文件
	vim /etc/ssh/sshd_config

	PubkeyAuthentication yes					# 启用基于密匙的安全验证
	PasswordAuthentication yes					# 启用基于口令的安全验证
	StrictModes no								# 关闭ssh在接收登录请求之前先检查用户家目录和rhosts文件的权限和所有权
	AuthorizedKeysFile .ssh/authorized_keys		# 指定公钥文件

# 在home目录添加文件
	.ssh/authorized_keys

	* 该文件, 一行配置一个客户端公钥


# 重启 ssh 服务
	systemctl restart sshd.service

	* 千万记得要先把公钥添加到了服务器, 才重启