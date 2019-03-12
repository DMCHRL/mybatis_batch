package com.dmchrl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dmchrl.dao")
public class MybatisBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisBatchApplication.class, args);
	}

}
