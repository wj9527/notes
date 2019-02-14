---------------------
discourse			 |
---------------------
	# github
		https://github.com/discourse/discourse

	# ��װ
		https://github.com/discourse/discourse_docker
		https://github.com/discourse/discourse/blob/master/docs/INSTALL-cloud.md
	

	# Docker��װ
		* �Ƴ��ɰ汾
yum remove docker \
docker-client \
docker-client-latest \
docker-common \
docker-latest \
docker-latest-logrotate \
docker-logrotate \
docker-selinux \
docker-engine-selinux \
docker-engine
		* ��װϵͳ����Ҫ�Ĺ���
			yum install -y yum-utils device-mapper-persistent-data lvm2
		
		* ��������Դ��Ϣ
			yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
		
		* ���� yum ����
			yum makecache fast
		
		* ��װ Docker-ce
			yum -y install docker-ce
		
		* ���� Docker ��̨����
			systemctl start docker
		
		* �������� hello-world
			docker run hello-world
		 
		* ɾ��docket ce
			yum remove docker-ce
			rm -rf /var/lib/docker
	

---------------------
launcher ά��		 |
---------------------
	�﷨: launcher COMMAND CONFIG [--skip-prereqs] [--docker-args STRING]
	COMMAND:
		start:      Start/initialize a container
			* ��ʼ��һ��container
		stop:       Stop a running container
			* ֹͣһ��container
		restart:    Restart a container
			* ����һ��container
		destroy:    Stop and remove a container
		enter:      Use nsenter to get a shell into a container
		logs:       View the Docker logs for a container
		bootstrap:  Bootstrap a container for the config based on a template
		rebuild:    Rebuild a container (destroy old, bootstrap, start new)
			* ���¹���
		cleanup:    Remove all containers that have stopped for > 24 hours

	Options:
		--skip-prereqs             Don't check launcher prerequisites
		--docker-args              Extra arguments to pass when running docker


---------------------
�����Ӱ�			 |
---------------------
	# �����Ʋ���ʱ�����ʼ�Ҫע��

		* ������������

DISCOURSE_SMTP_AUTHENTICATION: login
DISCOURSE_SMTP_OPENSSL_VERIFY_MODE: none
DISCOURSE_SMTP_ENABLE_START_TLS: true

		* �����������ļ����� run: ��һ�����ҵ�
			run:
			  - exec: echo "Beginning of custom commands"

			  ## If you want to set the 'From' email address for your first registration, uncomment and change:
			  #- exec: rails r "SiteSetting.notification_email='info@unconfigured.discourse.org'"
			  ## After getting the first signup email, re-comment the line. It only needs to run once. 

		* ɾ���� #- exec: rails r "SiteSetting.notification_email='info@unconfigured.discourse.org'"* ��һ�п�ͷ�� # ����
		* �ٰ� info@unconfigured.discourse.org �ĳ���ķ��������ַ
		* �༭�ļ���ʱ��Ҫɾ��ÿһ��ǰ�Ŀո��,�������������Ƕ����,��Ҫɾ��û˵��������

		Kev1nB!andy(ecs)=>ecs.run();
		Kev1nB!andy(happy)=> return happy*2;
		Kev1nB!andy(name)=>println(name);

---------------------
�����Ӱ�			 |
---------------------

---------------------
�����Ӱ�			 |
---------------------

	# ���ö���ȫ����ʾ��Ϣ
		���� -> δ���� > global notice

	# �������ӱ�ǩ����
		���� -> ��ǩ 
			min trust to create tag			������ǩ�������С���εȼ�
			min trust level to tag topics	������ӱ�ǩ����С���εȼ�
	
	# ���÷���ҳ�����ʽ(/categories)
		���� -> �������� -> desktop category page style
	
	# ������ҳ�Ĳ˵�(���Ӳ���)
		���� -> �������� -> top menu
		categories		����
		latest			����
		top				����
		posted			�ҵ�����
		new				
		red				�Ѷ�
		unred			δ��
		bookmarks		�ղ�
	
	# ������˻���
		���� -> ���� -> approve post count
		���� -> ���� -> approve unless trust level
		���� -> ���� -> approve new topics unless trust level
	
	# ѯ���Ƿ�����֪ͨ
		���� -> �������� -> push notifications prompt

	# ����֪ͨͼ��
		���� -> �������� -> push notifications icon

	# Github��¼
		���� -> ��¼ -> github

		* github�Ļص���ַ��
			{site}/auth/github/callback
	
	
	# �Զ�����
		���� -> ���� -> backup frequency
			* �����챸��һ��
		
		���� -> ���� -> allow restore
			* �������뱸�ݵ�����,����滻ȫվ������
	
	# ���û���ӭ˽������

	# �޸�ģ���������