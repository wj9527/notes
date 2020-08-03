--------------------------
swagger-ApiInfo
--------------------------
	# api��Ϣ������
	# ����ͨ�� ApiInfoBuilder �����ٹ���
	# ����
		ApiInfo apiInfo =  new ApiInfoBuilder()
			.version("0.0.0.1")											// �汾��
			.contact(new Contact("KevinBlandy", "https://springboot.io", "kevinblandy.cn@gmail.com"))  // ��ϵ��
			.description("�ÿ����Ե�") // ����
			.extensions(null) // ��Ӷ����չ
			.license("���֤") 	// api���֤
			.licenseUrl("https://springboot.io?license") // api���֤��ַ
			.termsOfServiceUrl("https://springboot.io/service") //  ���������ַ
			.title("swagger �ӿ��ĵ�") //  ����
			.build();
	
	# ��̬����
		// Ĭ����ϵ��
		public static final Contact DEFAULT_CONTACT = new Contact("", "", "");
		// Ĭ�ϵ�ʵ����
		public static final ApiInfo DEFAULT = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
	
	# ��չ
		* ��Ҫʵ�ֽӿ�: VendorExtension
			public interface VendorExtension<T> {
				String getName();
				T getValue();
			}
			
		* ���е�ʵ��
			ListVendorExtension
			ObjectVendorExtension
			StringVendorExtension

