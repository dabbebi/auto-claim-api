package com.autoclaim.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                		.allowedMethods("*")
                		.allowedOrigins("http://localhost:4200")
                        .allowedHeaders("*");
    }

}