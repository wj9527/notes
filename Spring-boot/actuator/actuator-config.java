management:
  endpoints:
    web:
      # 访问端点的base uri
      base-path: /actuator
      # 配置指定端点的映射 uri
      path-mapping:
        health:  health
      # 跨域的支持(CorsEndpointProperties)
      cors:
        allow-credentials: true
        allowed-methods: *
        max-age: 1800s
      # 外暴露的端点
      exposure:
        exclude:
        include:
          - '*'
    jmx:
      exposure:
        exclude:
        include:

    # 是否启用默认的端点(默认 true),禁用的端点将从应用程序上下文中完全删除
    enabled-by-default: true

  # 每个端点的配置
  endpoint:
    shutdown:
      enabled: true
    
    beans:
      enabled: true
      cache:
        time-to-live: 10s