----------------------
配置文件主体		  |
----------------------
	{
	  "log": {},
	  "api": {},
	  "dns": {},
	  "stats": {},
	  "routing": {},
	  "policy": {},
	  "reverse": {},
	  "inbounds": [],
	  "outbounds": [],
	  "transport": {}
	}

	# 的传入协议
		HTTP,SOCKS,VMess,Shadowsocks,Dokodemo-door

	# 传出协议有 
		VMess,Shadowsocks,Blackhole,Freedom,SOCKS
	
	# 日志的配置
		*  "log" 对象
			{
				"loglevel": "warning",
				"access": "/var/log/v2ray/access.log", 
				"error": "/var/log/v2ray/error.log"
			}

		* 日志级别:loglevel
			debug	最详细的日志信息，专用于软件调试
			info	比较详细的日志信息，可以看到 V2Ray 详细的连接信息
			warning	警告信息。轻微的问题信息，经我观察 warning 级别的信息大多是网络错误。推荐使用 warning
			error	错误信息。比较严重的错误信息。当出现 error 时该问题足以影响 V2Ray 的正常运行
			none	空。不记录任何信息

	# alterId 
		* 这个参数主要是为了加强防探测能力
		* 理论上 alterId 越大越好,但越大就约占内存(只针对服务器，客户端不占内存),所以折中之下设一个中间值才是最好的
		* 那么设多大才是最好的？其实这个是分场景的，我没有严格测试过这个，不过根据经验，alterId 的值设为 30 到 100 之间应该是比较合适的。
		* alterId 的大小要保证客户端的小于等于服务器的。

----------------------
一般配置			  |
----------------------
# 服务器配置
{
  "inbounds": [
    {
      "port": 16823, // 服务器监听端口
      "protocol": "vmess",    // 主传入协议
      "settings": {
        "clients": [
          {
            "id": "b831381d-6324-4d53-ad4f-8cda48b30811",  // 用户 ID，客户端与服务器必须相同
            "alterId": 64
          }
        ]
      }
    }
  ],
  "outbounds": [
    {
      "protocol": "freedom",  // 主传出协议
      "settings": {}
    }
  ]
}

 
# 客户端配置
{
  "inbounds": [
    {
      "port": 1080, // 监听端口
      "protocol": "socks", // 入口协议为 SOCKS 5
      "sniffing": {
        "enabled": true,
        "destOverride": ["http", "tls"]
      },
      "settings": {
        "auth": "noauth"  //socks的认证设置，noauth 代表不认证，由于 socks 通常在客户端使用，所以这里不认证
      }
    }
  ],
  "outbounds": [
    {
      "protocol": "vmess", // 出口协议
      "settings": {
        "vnext": [
          {
            "address": "serveraddr.com", // 服务器地址，请修改为你自己的服务器 IP 或域名
            "port": 16823,  // 服务器端口
            "users": [
              {
                "id": "b831381d-6324-4d53-ad4f-8cda48b30811",  // 用户 ID，必须与服务器端配置相同
                "alterId": 64 // 此处的值也应当与服务器相同
              }
            ]
          }
        ]
      },
	  "mux": {"enabled": true}
    }
  ]
}

