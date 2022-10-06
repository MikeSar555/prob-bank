package com.example.probbank.repository;

import com.example.probbank.domain.GatePay;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProbBankReactiveRepository extends ReactiveCrudRepository<GatePay, String> {

    Flux<GatePay>  findByParamsContaining(String s);
}
