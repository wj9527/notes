
# 查看所有的配置
git config --list 
	core.symlinks=false
	core.autocrlf=true
	core.fscache=true
	color.diff=auto
	color.status=auto
	color.branch=auto
	color.interactive=true
	help.format=html
	rebase.autosquash=true
	http.sslcainfo=C:/Program Files/Git/mingw64/ssl/certs/ca-bundle.crt
	http.sslbackend=openssl
	diff.astextplain.textconv=astextplain
	filter.lfs.clean=git-lfs clean -- %f
	filter.lfs.smudge=git-lfs smudge -- %f
	filter.lfs.process=git-lfs filter-process
	filter.lfs.required=true
	credential.helper=manager
	user.email=747692844@qq.com
	user.name=KevinBlandy
		* 用户的邮件和名称
	core.quotepath=false
	color.ui=true
		* 交互界面的文字带颜色

# 设置配置
	git config --global [配置项] [配置值]

	--global 
		* 表示全局,对所有仓库生效
		* 不添加的话,仅仅对当前仓库生效
	
	