package com.example.probbank.repository;

import com.example.probbank.domain.GatePay;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProbBankReactiveRepository extends ReactiveCrudRepository<GatePay, String> {

    Flux<GatePay>  findByParamsContaining(String s, final Pageable page);






    Flux<GatePay>  findByClientFioContaining(String f, final Pageable page);

    Flux<GatePay> findByParamsContaining(String param);

    Flux<GatePay> findByClientFioContaining(String fio);
}
