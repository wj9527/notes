--------------------
goaccess
--------------------
	# Nginx����־��������
		http://goaccess.io/
		https://www.goaccess.cc/?mod=features
		https://github.com/allinurl/goaccess
	
	# ��ʼ�������
		goaccess -f /usr/local/nginx/logs/access.log -p /etc/goaccess.conf -o /usr/local/nginx/html/goaccess.html --real-time-html --daemonize

		-f ָ��������־
		-p ָ�������ļ�
		-o ָ�����ɵ��ļ���ͨ����׺ȷ���ļ������ͣ�
		--real-time-html	����ʵʱˢ��
		--daemonize			��̨����
	
	# ���������ļ������ڸ�ʽ���쳣
		* �༭�����ļ�: /etc/goaccess.conf

		time-format %H:%M:%S
		date-format %d/%b/%Y
		log-format %h %^[%d:%t %^] "%r" %s %b "%R" "%u"
	
	# ����������ʾ
		* �༭�ļ�: /etc/sysconfig/i18n

		LANG="zh_CN.UTF-8"

		* ִ�����������Ч

		LANG="zh_CN.UTF-8"

		* ����goaccess��Nginx
	
