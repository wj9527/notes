-------------------------
email					 |
-------------------------
	# 参考
		https://www.liaoxuefeng.com/wiki/0014316089557264a6b348958f449949df42a6d3a2e542c000/001432005226355aadb8d4b2f3f42f6b1d6f2c5bd8d5263000#0
	# 核心的模块
		email
		smtplib
		nntplib
		Message
		|- MIMEBase
		   |- MIMEMultipart
		   |- MIMENonMultipart
			  |- MIMEMessage
			  |- MIMEText
			  |- MIMEImage
	# 大致步骤

-------------------------
发送普通邮件			 |
-------------------------
from email.header import Header
from email.mime.text import MIMEText
from email.utils import parseaddr, formataddr

import smtplib

# 如果地址信息包含中文，需要通过Header对象进行编码。
def format_address(address):
    name, addr = parseaddr(address)
    return formataddr((Header(name, 'utf-8').encode(), addr))


# 构建消息(消息内容,类型,编码)
# 发送HTML消息 MIMEText('<h1>Hello</h1>', 'html', 'utf-8')
message = MIMEText('Hello', 'plain', 'utf-8')

# 发送人
message['From'] = format_address('Javaweb开发者社区 <no-reply@javaweb.io>')

# 收件人,如果有多个地址,使用逗号分隔字符串
message['To'] = format_address('KevinBlandy <747692844@qq.com>')

# 主题
message['Subject'] = Header('欢迎你啊', 'utf-8').encode()

# 新建STMP实例(地址,端口)
server = smtplib.SMTP_SSL('smtp.exmail.qq.com', 465)
# 如果不是SSL的连接:smtplib.SMTP(host,port)

# 设置DEBUG级别
server.set_debuglevel(1)

# 使用账户名密码登录
server.login('no-reply@javaweb.io', '123456789')

# 发送邮件,注意发送人账户必须和登录的账户名一样
server.sendmail('no-reply@javaweb.io', '747692844@qq.com', message.as_string())

# 退出客户端
server.quit()

-------------------------
发送带附件的邮件		 |
-------------------------
