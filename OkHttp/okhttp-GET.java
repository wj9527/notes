---------------------
Get					 |
---------------------
	String run(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

---------------------
文件下载			 |
---------------------
	OkHttpClient client = new OkHttpClient();
	Request request = new Request.Builder().url("http://localhost/1.mp4").build();
	Response response = client.newCall(request).execute();
	
	// 从响应获取到InputStream
	BufferedInputStream bufferedInputStream = new BufferedInputStream(response.body().byteStream());
	
	// io到本地
	BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("D:\\演讲.mp4")));
	byte[] data = new byte[1024];
	int len;
	while((len = bufferedInputStream.read(data)) != -1) {
		bufferedOutputStream.write(data,0,len);
		bufferedOutputStream.flush();
	}
	bufferedOutputStream.close();