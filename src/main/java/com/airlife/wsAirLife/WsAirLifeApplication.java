package com.airlife.wsAirLife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class WsAirLifeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(WsAirLifeApplication.class, args);
	}

}
