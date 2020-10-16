package com.ypc.devopstest;

import cn.hutool.core.util.RandomUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class StartApplication {

	private static String randStr = "";
	public static void main(String[] args) {
		randStr = RandomUtil.randomString(20);
		SpringApplication.run(StartApplication.class, args);
	}

	@GetMapping("/health")
	public Integer health() {
		System.out.println("收到health请求");
		return 200;
	}

	@GetMapping("/check")
	public String check(){
		System.out.println("收到check请求:"+randStr);
		return randStr;
	}

}
