package com.cyjz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import tk.mybatis.spring.annotation.MapperScan;

@EnableDubbo
@SpringBootApplication
public class TyServiceStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TyServiceStarterApplication.class, args);
	}
}