----------------------------
v2ry						|
----------------------------
	# 地址
		https://www.v2ray.com
		https://github.com/v2ray/v2ray-core
	
----------------------------
官方安装					|
----------------------------
	# 下载并安装 V2Ray
		wget https://install.direct/go.sh
		bash go.sh

		* yum 或 apt-get 可用的情况下,此脚本会自动安装 unzip 和 daemon
		* 这两个组件是安装 V2Ray 的必要组件
		* 如果系统不支持 yum 或 apt-get,请自行安装 unzip 和 daemon
	
		* 此脚本会自动安装以下文件
			/usr/bin/v2ray/v2ray		V2Ray 程序
			/usr/bin/v2ray/v2ctl		V2Ray 工具
			/etc/v2ray/config.json		配置文件
			/usr/bin/v2ray/geoip.dat	IP 数据文件
			/usr/bin/v2ray/geosite.dat	域名数据文件
		
		* 运行脚本位于系统的以下位置
			/etc/systemd/system/v2ray.service: Systemd
			/etc/init.d/v2ray: SysV
		
		
		* 脚本运行完成后，你需要：
	
	# 编辑文件
		/etc/v2ray/config.json
	
	# 启动和维护
		systemctl start v2ray

		start
		stop
		status
		reload
		restart
		force-reload // 强制重新加载
	
	# 升级和更新
		* 再次下载脚本,重新安装就OK
	
	# 客户端的下载
		https://github.com/v2ray/v2ray-core/releases
						

----------------------------
v2ray	命名行参数			|
----------------------------
	-version
		* 只输出当前版本然后退出,不运行 V2Ray 主程序



