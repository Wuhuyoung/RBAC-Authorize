package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AuthorizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizeApplication.class, args);
		log.info("服务器启动");
	}

}
