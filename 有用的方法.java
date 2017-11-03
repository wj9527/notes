------------------------------------------
把字节数组,转换为16进制字符串			  |
------------------------------------------
private String bytesToHexString(byte[] src) {
	StringBuilder stringBuilder = new StringBuilder();
	if (src == null || src.length <= 0) {
		return null;
	}
	for (int i = 0; i < src.length; i++) {
		int v = src[i] & 0xFF;
		String hv = Integer.toHexString(v);
		if (hv.length() < 2) {
			stringBuilder.append(0);
		}
		stringBuilder.append(hv);
	}
	return stringBuilder.toString();
}

------------------------------------------
把一个字节,转换为16进制字符串			  |
------------------------------------------
private static String toHex(byte b) {  
	String result = Integer.toHexString(b & 0xFF);  
	if (result.length() == 1) {  
		result = '0' + result;  
	}  
	return result;  
}  

------------------------------------------
把16进制字符串,转换为字节数组			  |
------------------------------------------
private byte[] hexStringToBytes(String hexString) {
	if (hexString == null || hexString.equals("")) {
		return null;
	}
	hexString = hexString.toUpperCase();
	int length = hexString.length() / 2;
	char[] hexChars = hexString.toCharArray();
	byte[] d = new byte[length];
	for (int i = 0; i < length; i++) {
		int pos = i * 2;
		d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
	}
	return d;
}
/**
 * Char -> byte
 * */
private byte charToByte(char cha) {
	return (byte) "0123456789ABCDEF".indexOf(cha);
}
------------------------------------------
获取客户端IP							  |
------------------------------------------
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	

------------------------------------------
 AES算法								 |
------------------------------------------
	/**
	 * AES算法
	 * 128位密钥,ECB算法,PKCS5Padding填充模式加密
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] sSrc, byte[] sKey) throws Exception {  
		SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES"); 
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);  
		byte[] encrypted = cipher.doFinal(sSrc);  
		return encrypted;
	}  


------------------------------------------
 GIZ压缩/解压缩							  |
------------------------------------------
 /**
     * 解压缩
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] unGZip(byte[] data) throws IOException {
        byte[] bytes = null;
        ByteArrayInputStream byteArrayInputStream = null;
        GZIPInputStream gzipInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(data);
            gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] buf = new byte[1024];
            int num = -1;
            byteArrayOutputStream = new ByteArrayOutputStream();
            while ((num = gzipInputStream.read(buf, 0, buf.length)) != -1)
            {
                byteArrayOutputStream.write(buf, 0, num);
            }
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
        } finally {
            byteArrayInputStream.close();
            gzipInputStream.close();
            byteArrayOutputStream.close();
        }
        return bytes;
    }

    /**
     * 压缩
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] gZip(byte[] data) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        GZIPOutputStream gzipOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(data);
            gzipOutputStream.finish();
            bytes = byteArrayOutputStream.toByteArray();
        } finally {

        }
        return bytes;
    }



------------------------------------------
MD5										  |
------------------------------------------
private static String encodeMd5(byte[] source) {
	try {
		return encodeHex(MessageDigest.getInstance("MD5").digest(source));
	} catch (NoSuchAlgorithmException e) {
		throw new IllegalStateException(e.getMessage(), e);
	}
}

private static String encodeHex(byte[] bytes) {
	StringBuffer buffer = new StringBuffer(bytes.length * 2);
	for (int i = 0; i < bytes.length; i++) {
		if (((int) bytes[i] & 0xff) < 0x10)
			buffer.append("0");
		buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
	}
	return buffer.toString();
}