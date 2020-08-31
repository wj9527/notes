----------------------------
ResponseCookie
----------------------------
	# ���ڹ�����Ӧ���ͻ���Cookie��Ϣ

		@GetMapping("/test")
		public Object test (HttpServletRequest request,
						HttpServletResponse response) throws Exception {
			
			ResponseCookie cookie = ResponseCookie.from("myCookie", "myCookieValue") // key & value
					.httpOnly(true)		// ��ֹjs��ȡ
					.secure(false)		// ��http��Ҳ����
					.domain("localhost")// ����
					.path("/")			// path
					.maxAge(Duration.ofHours(1))	// 1��Сʱ�����
					.sameSite("Lax")	// ��������Ҳ�ǲ����͵����� Cookie�����ǵ�����Ŀ����ַ�� Get �������
					.build()
					;
			
			// ����Cookie
			response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
			
			return "ok";
		}
	
	# ��Ҫ�� sameSite ���ԣ� servlet��ûʵ�֡��Ȳ����ѿ���ʹ��ResponseCookie