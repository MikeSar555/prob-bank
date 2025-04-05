package com.example.probbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.probbank.domain.GatePay;
import com.example.probbank.repository.ProbBankReactiveRepository;

import reactor.core.publisher.Flux;

@EnableEurekaClient
@SpringBootApplication
public class ProbBankApplication {
	@Autowired
	ProbBankReactiveRepository prob;

	public static void main(String[] args) {
		SpringApplication.run(ProbBankApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
		/*
		 * Flux<GatePay> payFlux = prob.findByParamsContaining("902717", new
		 * Pageable(0,55) {
		 * });
		 * ArrayList<GatePay> g_list = (ArrayList<GatePay>)
		 * payFlux.collectList().share().block();
		 * int g_list_size = g_list.size();
		 * for(GatePay g:g_list) {
		 * System.out.println("Id "+g.getId()+" date "+g.getEventDate()+" amount "+g.
		 * getAmount()+" params  "+g.getParams()+" fio  "+g.getClientFio() );
		 * 
		 * }
		 * // return g_list;
		 * return "htllo "+String.valueOf(g_list_size);
		 */
		return null;
	}

	/*
	 * @GetMapping(path = "/paysByParam", produces = "text/event-stream")
	 * public Flux<GatePay> getPaysByParams(@RequestParam(value = "param",
	 * defaultValue = "www") String par) {
	 * System.out.println(" запущен поиск по параметру...");
	 * Flux<GatePay> payFlux = prob.findByParamsContaining(par);
	 * return payFlux;
	 * 
	 * }
	 */

}
