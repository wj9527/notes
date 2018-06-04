
# 获取文件的hash值
	certutil -hashfile [文件] [hash算法]

	* hash算法可以是: md5,sha1,sha256

# 清除由于网络问题带来的,maven依赖下载失败遗留的 .lastupdate文件
	for /r %i in (*.lastUpdated)do del %i

	* 需要在maven的仓库目录执行