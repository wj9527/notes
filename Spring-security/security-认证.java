------------------
认证			  |
------------------
	# 各个组件
		UsernamePasswordAuthenticationFilter


		UsernamePasswordAuthenticationToken

		AuthenticationManager

		AuthenticationProvider

		GrantedAuthority
			* 表示角色信息接口
			* 实现
				JaasGrantedAuthority
				SimpleGrantedAuthority
				SwitchUserGrantedAuthority

		UserDetailsService
			* 根据用户名, 检索用户的接口
			* 只有一个方法
				UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
				
				* 该方法只要返回null, 就表示认证失败
			
			
		UserDetails
			* 表示用户的详情接口
			* 抽象方法
				Collection<? extends GrantedAuthority> getAuthorities();
				String getPassword();
				String getUsername();
				boolean isAccountNonExpired();
				boolean isAccountNonLocked();
				boolean isCredentialsNonExpired();
				boolean isEnabled();
			
			* 子实现类
				User
		
		PasswordEncoder
			* 密码的编码接口
			* 实现类
				BCryptPasswordEncoder
				DelegatingPasswordEncoder