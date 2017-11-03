
1,加密/解密工具包
	<dependency>
		<groupId>commons-codec</groupId>
		<artifactId>commons-codec</artifactId>
		<version>1.10</version>
	</dependency>
	//执行MD5加密
	String md5String = DigestUtils.md5Hex("123456")
	//执行Base64编码
	String base64String = Base64.encodeBase64String("余文朋".getBytes());
	//执行Base64解码
	byte[] bytes = Base64.decodeBase64(base64String.getBytes());
	String results = new String(bytes);

