package com.xk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xk.adm.domain.dao.mapper")
public class XkBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(XkBaseApplication.class, args);
	}

}
