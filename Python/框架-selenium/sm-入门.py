----------------------------
入门						|
----------------------------
	* 安装
		pip install selenium
	
----------------------------
基本的操作					|
----------------------------
	# 导入 webdriver
	from selenium import webdriver

	# 想要调用键盘操作,需要引入 keys 包
	from selenium.webdriver.common.keys import Keys

	# 调用环境变量指定的 PhantomJS 浏览器创建浏览器对象
	# driver = webdriver.PhantomJS()

	# 如果没有在环境变量指定 PhantomJS 的位置(其实就是指定 phantomjs执行文件的地址)
	driver = webdriver.PhantomJS(executable_path='./phantomjs')

	# ger方法会一直等到页面完全被加载,然后才会继续程序的执行
	driver.get('https://www.baidu.com')

	# 生成页面快照
	driver.save_screenshot('baidu.png')

	# 找到id为kw的元素(input),在里面写入 "Hello"
	driver.find_element_by_id('kw').send_keys("Hello")

	# 找到id为id的元素,执行点击它
	driver.find_element_by_id('su').click()

	# 生成页面快照
	driver.save_screenshot('hello.png')

	# 打印网页源码
	print(driver.page_source)

	# 获取所有的 cookie
	print(driver.get_cookies())

	# 获取指定名称的cookie
	#driver.get_cookie('JESSIONID')

	# ctrl + a 全选输入框内容
	driver.find_element_by_id('kw').send_keys(Keys.CONTROL,'a')

	# ctrl + x 剪切输入框内容
	driver.find_element_by_id('kw').send_keys(Keys.CONTROL,'x')

	# 输入框重新输入内容
	driver.find_element_by_id('kw').send_keys('KevinBlandy')

	# 模拟enter回车键
	driver.find_element_by_id('su').send_keys(Keys.ENTER)

	# 清除输入框内容
	driver.find_element_by_id('kw').clear()

	# 获取当前Url
	print(driver.current_url)

	# 关闭当前页面,如果只有一个页面会关闭浏览器
	driver.close()

	# 关闭浏览器
	driver.quit()

----------------------------
元素获取					|
----------------------------
	# 根据id检索
	driver.find_element_by_id()
	# 根据名字检索
	driver.find_element_by_name()
	driver.find_element_by_xpath()
	driver.find_element_by_link_text()
	driver.find_element_by_partial_link_text()
	# 根据标签名称检索
	driver.find_element_by_tag_name()
	# 根据class名称检索
	driver.find_element_by_class_name()
	# 根据css检索
	driver.find_element_by_css_selector()

----------------------------
弹窗处理					|
----------------------------
	* 处理页面的alert弹窗
	alert = driver.switch_to_alert()

----------------------------
页面切换					|
----------------------------
	driver.switch_to_window('name')

----------------------------
页面前进和后退				|
----------------------------
	driver.forward()	# 前进
	driver.back()		# 后退
	
----------------------------
cookie						|
----------------------------
	* 获取所有的 cookie
		driver.get_cookies()

	* 获取指定名称的cookie
		driver.get_cookie('JESSIONID')
	
	* 删除cookie
		driver.delete_cookie('name')
	
	* 删除所有cookie
		driver.delete_all_cookies()

	
----------------------------
页面等待					|
----------------------------
	* 网站都几乎有才用ajax异步加载,有些元素是通过异步加载才会被载入dom内存
	* 有两种方式处理这个问题
	* .... ...

	* 显示等待,显示指定某个条件,然后设置最长等待时间,如果超时还未找到元素,抛出异常
		

