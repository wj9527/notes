--------------------------
recaptcha
--------------------------
	# 注册地址（需要翻墙）
		https://www.google.com/recaptcha/admin/create
	
	# 管理控制台
		https://www.google.com/recaptcha/admin/site/350006937
	
	# 文档地址
		https://developers.google.com/recaptcha/intro
	
	
	# 客户端集成
		<script src="https://www.google.com/recaptcha/api.js?render=_reCAPTCHA_site_key"></script>
		<script>
			grecaptcha.ready(function() {
				grecaptcha.execute('_reCAPTCHA_site_key_', {action: 'homepage'}).then(function(token) {
				
				});
			});
		</script>
		

		* grecaptcha.ready 会在reCAPTCHA库加载时运行

		* '_reCAPTCHA_site_key_' 是网页密钥

		* 通过执行: grecaptcha.execute(...) 方法来执行验证
	
		* action参数, 表示验证的场景，可以自定义。在后台，可以针对不同的场景做不同的设置
			homepage		主页
			login			登录
			social			社交
			e-commerce		电商
		
		* token 需要提交给后端，后端通过验证API判断请求是否合法

		* (Fuck GFW)国内的用户，需要把js库的地址更换为: https://www.recaptcha.net/recaptcha/api.js

			
	
	# 服务端继承
		* POST 接口
			https://www.google.com/recaptcha/api/siteverify
				secret			服务端的 secret
				response		客户端生成的token
				remoteip		可选的参数，客户的ip地址
		
		* (Fuck GFW)国内的用户，需要把api地址更换为: https://www.recaptcha.net/recaptcha/api/siteverify
		
		* 响应
			{
			  "success": true|false,      // 此请求是否是站点的有效reCAPTCHA令牌
			  "score": number             // 此请求的分数（0.0-1.0），人机判断的参考值。1 是人类，0是机器。
			  "action": string            // 定义的验证场景
			  "challenge_ts": timestamp,  // 质询加载的时间戳（ISO格式yyyy-MM-dd'T'HH:MM:ssZZ）
			  "hostname": string,         // 使用reCAPTCHA的站点的主机名
			  "error-codes": [...]        // 可选的错误代码
			}
		
			* error-codes 异常状态码
				Error code				Description
				missing-input-secret	secret参数丢失。
				invalid-input-secret	secret参数无效或格式错误。
				missing-input-response	缺少响应参数。
				invalid-input-response	响应参数无效或格式错误。
				bad-request	The request 该请求无效或格式错误。
				timeout-or-duplicate	响应不再有效：太旧或以前使用过。
	

	# 隐藏reCAPTCHA图标
		* 添加css
			.grecaptcha-badge { 
				display: none; 
			} 

	