package com.wolfsoft.co;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.wolfsoft.co.domain.Account;
import com.wolfsoft.co.repository.AccountRepository;

@SpringBootApplication
@EnableResourceServer
public class MyMenuServiceApplication {

	@Bean
	CommandLineRunner demo(AccountRepository repository){
		if(repository.findAll().isEmpty()){
			repository.save(new Account("fheras.garcia@gmail.com", "Master", true));
			repository.save(new Account("sandracantillo@gmail.com", "12345", true));
		}
		return null;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MyMenuServiceApplication.class, args);
	}
}