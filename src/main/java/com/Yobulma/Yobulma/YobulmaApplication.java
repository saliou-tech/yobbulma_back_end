package com.Yobulma.Yobulma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class YobulmaApplication  extends SpringBootServletInitializer  {


	@Override
	protected org.springframework.boot.builder.SpringApplicationBuilder configure(org.springframework.boot.builder.SpringApplicationBuilder builder) {
		return super.configure(builder);
	}

	public static void main(String[] args) {
		SpringApplication.run(YobulmaApplication.class, args);
	}
/*@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return  new BCryptPasswordEncoder();
	}*/
	@Bean
	public  SpringApplicationContext springApplicationContext(){
		return  new SpringApplicationContext();
	}

}
