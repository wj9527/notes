---------------------------
XMLHttpRequest				|
---------------------------
	# ����
		function createXMLHttpRequest(){
				try{
					//����������
					return new XMLHttpRequest();
				}catch(e){
					try{
						return new ActiveXObject("Msxml2.XMLHTTP");//IE6.0
					}catch(e){
						try{
							return new ActiveXObject("Microsoft.XMLHTTP");//IE5.5�Լ�����汾
						}catch(e){
							alert("Ϊ�˸��õ�����,������������������ʱ�վ");
							throw e;
						}
					}
				}
			}
		
	# ʵ������
		open(method,url,async)
			* ��һ������,
			* ��һ������,���ַ�������ʽָ������ʽ
			* �ڶ�������,���ַ���,URL,
			* ����������boolean,�Ƿ�Ϊ�첽(Ĭ��true)
		setRequestHeader(header,value)
			* ��������ͷ,��һ�������� ����ͷ��key,�ڶ�������������ͷ��value
		send()
			* ����������,�����GET����,��÷���Ӧ������Ϊnull(����������������)
			* ����������URL�������ַ���,Ҳ������ FormData ����
		
		getResponseHeader(name)
			* ��ȡָ�����Ƶ���Ӧͷ

		getAllResponseHeaders()
			* ��ȡ���е���Ӧͷ
		

	# ʵ������
		readyState
			* ����״̬��
				> 0״̬:�մ���,��û�е���oppen();����
				> 1״̬:����ʼ,������oppen();����,����û����send����
				> 2״̬:���������,��������send����
				> 3״̬:�������Ѿ���ʼ��Ӧ,������ʾ��Ӧ������
				> 4״̬:��������Ӧ����(ͨ������ֻ�������״̬...)
		status
			* HTTP��Ӧ��
		
		timeout
			* ��ʱʱ��,��λ(ms)
			* Ĭ�� 120 s
		
		responseType	
			* �����������Ӧ����������
			* ��ѡֵ
				arraybuffer,
				blog,	

		responseXML
			* ���ط�������Ӧ��XML����

		responseText
			* ���ط�������Ӧ��TEXT����

		withCredentials
			* �ڿ��������ʱ��,�Ƿ��CookieֵҲ���͵����������
			* ������Ӧ���� open() ����ִ��֮ǰ����
		
		upload
			* ���ύ�ı����ļ�����ʱ��,�����Ի����
			* �����Կ��Լ���һ���ϴ��¼�:progress
				if(xhr.upload){
					//�����ϴ����Ե��ϴ��¼�,ÿ���ϴ��¼�����ִ�� progressHandlingFunction
					xhr.addEventListener('progress',progressHandlingFunction, false);
					//xhr.upload.onprogress = function(){}			Ҳ����
				}
			* Event����
					total;		//��ȡ�ϴ��ļ�����(����)��С
					loaded;		//��ȡ�Ѿ��ϴ����ļ���С

					function progressHandlingFunction(event) {
						event.total;		//��ȡ�ϴ��ļ����ܴ�С
						event.loaded;		//��ȡ�Ѿ��ϴ����ļ���С
						//��ȡ���ȵİٷֱ�ֵ
						var percent  = (event.loaded / event.total) * 100;
						//�������뱣����λС��
						percent = percent.toFixed(2);
					}
	
		statusText
			* http״̬����������
		
		onload
			* ����readyState = 4 ʱ��ִ�еĻص�,�����Դ��������ж�
				if(xmlHttp.readyState == 4 && xmlHttp.status == 200){}
			
			* ʹ�� onload
				let xhr = new XMLHttpRequest();
				xhr.onload = function () {
					// �õ��ͻ��˵���Ӧ
					let json = JSON.parse(xhr.responseText);
				};
				xhr.open('GET', '/foo', true);
				xhr.send(null);

	# �¼�
		onreadyStatechange
			* ���첽�����״̬�����ı��ʱ�����
			* ��ʹ�� async=false ʱ�����ñ�д onreadystatechange ����,�Ѵ���ŵ� send() �����漴��
				
		
---------------------------
XMLHttpRequest-GET			|
---------------------------
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.open("GET", "/test", true);
	xmlHttp.send(null);//GET����û��������,����ҲҪ����null,��ȻFireFox�����޷�����
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			//��ȡ����˵���Ӧ
			var text = xmlHttp.responseText;
		}
	}

---------------------------
XMLHttpRequest-POST			|
---------------------------
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.open("POST", "/test", true);		
	//������ΪPOST��ʱ��,��Ҫ�ֶ������ͷ
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");	
	//������
	xmlHttp.send("userName=kevin&passWord=123456");				
	xmlHttp.onreadystatechange = function()	{					
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){	
			//��ȡ����˵���Ӧ
			var text = xmlHttp.responseText;
		}
	}

---------------------------
XMLHttpRequest-������		|
---------------------------
			// �򿪼��ض���
			const index = layer.load(1, {
  				shade: [0.1,'#fff']
			});
			
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/download?file=' + encodeURIComponent(file));
			xhr.send(null);
			// ���÷���˵���Ӧ����
			xhr.responseType = "blob";
			// ��������
			xhr.addEventListener('progress', event => {
				// ������ٷֱ�
				const percent  = ((event.loaded / event.total) * 100).toFixed(2);
				console.log(`���ؽ��ȣ�${percent}`);
			}, false);
			xhr.onreadystatechange = event => {
				if(xhr.readyState == 4){
					if (xhr.status == 200){
						
						// ��ȡContentType
						const contentType = xhr.getResponseHeader('Content-Type');
						
						// �ļ�����
						const fileName = xhr.getResponseHeader('Content-Disposition').split(';')[1].split('=')[1];
						
						// ����һ��a��ǩ��������
						const donwLoadLink = document.createElement('a');
						donwLoadLink.download = fileName;
						donwLoadLink.href = URL.createObjectURL(xhr.response);
						
						// ���������¼���IO������
						donwLoadLink.click();
						
						// �ͷ��ڴ��е���Դ
						URL.revokeObjectURL(donwLoadLink.href);
						
						// �رռ��ض���
						layer.close(index);
					} else if (response.status == 404){
						alert(`�ļ���${file} ������`);
					} else if (response.status == 500){
						alert('ϵͳ�쳣');
					}
				}
			}
		}