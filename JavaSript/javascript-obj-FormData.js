-----------------------
FormData				|
-----------------------
	# html5�¶���
	# ����ʵ���첽���ϴ��ļ�,����
	# ����
		var formData = new FormData();
			* ֱ�Ӵ���

		var formData = new FormData(document.getElementById('form'));
			* ͨ�����е�form�����д���
		
		var form =  document.getElementById("form");

		var formData = form.getFormData()
			* ͨ�����е�form�����д���

-----------------------
FormData	����		|
-----------------------

-----------------------
FormData	����		|
-----------------------
	append(key,value, filename);
		* ���һ������

		value
			* ������һ���ļ�����
		filename 
			* ����ʡ��, ��ʾ�������������ļ�����
			* ��һ�� Blob �� File ����Ϊ�ڶ���������ʱ��, Blob �����Ĭ���ļ����� "blob", File �����Ĭ���ļ����Ǹ��ļ������ơ�
		
			* blob�����������ContentType
				const json = JSON.stringify({'name': 'KeviniBlandy'});
				formData.append('json', new Blob([json], {type: "application/json"}));

	set(key,value);
		* �� appendһ��
		* �� append() �������ǣ���ָ���� key ֵ����ʱ��append() �����ǽ���������ӵ����Եļ�ֵ����󣬶�set()�������Ḳ��ǰ������õļ�ֵ�ԡ�
	
	delete(key);
		* ɾ��ָ����key
	
	get(key);
		* ���ص�һ��key��Ӧ��ֵ

	getAll(key);
		* ��������key��Ӧ��ֵ,���ص���һ������
	
	has(key);
		* �ж��Ƿ��и�ֵ
	
	keys();
		* û�в���,���ص�����,�����е�keyֵ
		*	for (var key in formData.keys()) {
			   console.log(key); 
			}

	values();
		* ͬ��,������values�ĵ���
	
	entries();
		* �е���map���Ǹ��ڲ���,���Ի�ȡ��key��value
		*	for(var pair in formData.entries()) {
				console.log(pair[0]+ ', '+ pair[1]); 
			}
-----------------------
FormData	�¼�		|
-----------------------


-----------------------
FormData	Demo		|
-----------------------
	
	# ʹ�� XMLHttpRequest ���б��ύ
		var xhr = createXMLHttpRequest();
		xhr.open("POST", "${ctxPath}/test/upload", true);
		xhr.send(formData);
		xhr.onreadystatechange = function()	{
			if(xhr.readyState == 4 && xhr.status == 200){
				var text = xhr.responseText;
				JSON.parse(text);
			}
		}
		
	# ����Jquery�첽�ϴ�ʾ��
		<input id="file_upload" onchange="fileUpdload();" type="file"  accept="image/gif,image/jpeg,image/png"/>
		function fileUpdload() {
			//����formData����
			var form = new FormData();	
			//��ȡ��ѡ���ļ�����,ע��,files();���������Ǹ�����,��Ϊ�п����Ƕ���ļ�
			var file = $("#file_upload")[0].files[0];
			//����ļ���formData����,��һ�����������ϴ��ļ���name����
			form.append("imgFile",file );
			$.ajax({
				url : '${pageContext.request.contextPath}/file/uploadFile',
				type : 'POST',
				data : form,
				processData : false,
				contentType : false,
				success : function(response) {

				},
				error : function(response) {

				}
			});
		}
	
	# �ļ��������
		multiple="multiple"
			* ��������Դ���,���ʾ�����ѡ
		accept="image/gif,image/jpeg,image/png"
			* ��ֵ��ʾ���и��ļ���ѡ����Щ�ļ�

	# �ļ��Ĳ�������
		1, ��ȡ�ļ������
			* ԭ���ķ�ʽ��ȡ
				var fileInput = document.getElementById("#file_upload");
			* JQ��ȡ
				var fileInput = $("#file_upload")[0];
		
		2,�Ӷ����ȡ�ļ��б�
			* ʹ���ļ�������:files();������ȡ
				var files = fileInput.files();		//ע��,�� files �Ǹ�����,��Ϊ�ļ������ѡ��Ķ���ļ�

			* ����
				lastModified
					* ���һ���޸ĵ�ʱ���
				lastModifiedDate
					* ���һ���޸�ʱ��
				name
					* �ļ�����
				size
					* �ļ���С
				type
					* �ļ�����
				webkitRelativePath
					* ���·��
			
			* WEB��ʾ����ͼƬ
				var url = window.URL.createObjectURL(files[0]);		//���ڴ��и����ı�������һ�������ƶ���
                $('#img').attr({'src':url});						//ֱ�Ӱ���������ƶ�����ʾ��img,Ҫע������ʾ֮��,�ͷŵ��ڴ�
				window.URL.revokeObjectURL(url);					//�ͷ��ڴ�

	# ���Easyui��ȡ File ���������
		* �ļ������
			<input
				accept="image/gif,image/jpeg,image/png"
				class="easyui-filebox"
				data-options="
					onChange:fileUpdload
				"/>
		
		* onChange ���ϴ��¼�
			function fileUpdload() {
				//��ֵ��һ���ַ���,������ȡ���Ǳ����ļ���·��ֵ,�����ܻ�ȡ��file����
				var value = $(this).filebox('getValue');
				TD.println(value);
				
				//�ö������һ��file���������,��Ϊfile�п����Ƕ�ѡ
				var files = $('#filebox_file_id_1')[0].files;
				TD.println(files);
			}
		
		* filebox_file_id_1 ���ID ��easyui �Լ�������input ��ǩ. �����������������ļ��ĵط�.
		* ��������˶�� filebox  ��ô�����ID ����filebox_file_id_2,filebox_file_id_3.
	
		
