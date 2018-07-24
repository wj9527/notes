---------------------
POST				 |
---------------------
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	OkHttpClient client = new OkHttpClient();
	String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

---------------------
构建普通表单体		 |
---------------------
	RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{}");
	RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), "name=KevinBlandy");

---------------------
构建文件表单体		 |
---------------------
	File file = new File("");
	RequestBody requestBody = new MultipartBody.Builder()
	.setType(MultipartBody.FORM)
	.addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
	.addFormDataPart("name", "KevinBlandy")
	.build();