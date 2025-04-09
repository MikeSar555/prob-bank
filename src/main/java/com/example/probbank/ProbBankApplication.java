package com.example.probbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.example.probbank.repository.ProbBankReactiveRepository;

@EnableEurekaClient
@SpringBootApplication
public class ProbBankApplication {
	@Autowired
	ProbBankReactiveRepository prob;

	public static void main(String[] args) {
		SpringApplication.run(ProbBankApplication.class, args);
	}
}
