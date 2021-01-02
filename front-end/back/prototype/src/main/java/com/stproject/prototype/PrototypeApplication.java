package com.stproject.prototype;

import com.stproject.prototype.controler.ContractControler;
import com.stproject.prototype.controler.UserControler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrototypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}

	@Bean
	public UserControler getUserControler() { return new UserControler(); }

	@Bean
	public ContractControler getContractControler() { return new ContractControler(); }

}
