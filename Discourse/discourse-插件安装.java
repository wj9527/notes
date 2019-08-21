--------------------------------
插件安装						|
--------------------------------
	# 官方教程
		https://meta.discourse.org/t/install-plugins-in-discourse/19157
	
	# 步骤
		1. 获取到插件的git地址(github/bitbucket)
		2. 进入程序目录, 编辑: app.yml
			cd /var/discourse
			vim containers/app.yml
		
		3. 添加插件地址到hock
			hooks:
			  after_code:
				- exec:
					cd: $home/plugins
					cmd:
					  - git clone https://github.com/discourse/docker_manager.git
					  - git clone https://github.com/discourse/discourse-spoiler-alert.git
		4. 重构容器
			cd /var/discourse
			./launcher rebuild app
		
--------------------------------
一些插件统计					|
--------------------------------
	# 官方的插件统计
		https://meta.discourse.org/c/plugin
	
	# 提问/解决插件
		https://github.com/discourse/discourse-solved.git
	
	# 谷歌广告插件
		https://github.com/discoursehosting/discourse-adsense.git

		* CSP策略需要添加两个白名单
			pagead2.googlesyndication.com
			adservice.google.com

base-uri 'none'; 
object-src 'none'; 
script-src 'unsafe-eval' 
'report-sample' 
https://forum.springboot.io/logs/ 
https://forum.springboot.io/sidekiq/ 
https://forum.springboot.io/mini-profiler-resources/ 
https://forum.springboot.io/assets/ 
https://forum.springboot.io/brotli_asset/ 
https://forum.springboot.io/extra-locales/ 
https://forum.springboot.io/highlight-js/ 
https://forum.springboot.io/javascripts/ 
https://forum.springboot.io/plugins/ 
https://forum.springboot.io/theme-javascripts/ 
https://forum.springboot.io/svg-sprite/ 
hm.baidu.com 
zz.bdstatic.com
push.zhanzhang.baidu.com 
pagead2.googlesyndication.com 
adservice.google.com; 
