package com.example.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//스프링부트의 main메소드를 실행하면 여러가지 설정이 읽히고 톰캣도 실행된다. 이렇게 실행되는 이유가 @SpringBootApplication 안에 있는 어노테이션들 때문이다.
@SpringBootApplication
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

}
