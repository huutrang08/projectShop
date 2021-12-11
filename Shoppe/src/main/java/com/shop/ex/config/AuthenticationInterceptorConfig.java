package com.shop.ex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shop.ex.interceptor.AdminAuthenticationInterceptor;
import com.shop.ex.interceptor.UserAuthenticationInterceptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;
	@Autowired
	private UserAuthenticationInterceptor userAuthenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/admin/**");
		registry.addInterceptor(userAuthenticationInterceptor).addPathPatterns("/cart/**");
		registry.addInterceptor(userAuthenticationInterceptor).addPathPatterns("/user/profile");
		registry.addInterceptor(userAuthenticationInterceptor).addPathPatterns("/user/change");
		registry.addInterceptor(userAuthenticationInterceptor).addPathPatterns("/user/changepass");
		
	}
	
	
      
}
