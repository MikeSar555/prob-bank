package com.example.probbank.service;

import com.example.probbank.domain.GatePay;
import com.example.probbank.repository.ProbBankReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GatePayService {

    private final ProbBankReactiveRepository probBankReactiveRepository;

    @Autowired
    public GatePayService(ProbBankReactiveRepository probBankReactiveRepository) {
        this.probBankReactiveRepository = probBankReactiveRepository;
    }

    public Flux<GatePay> paysByParams(String param, PageRequest pr){
        return probBankReactiveRepository.findByParamsContaining(param, PageRequest.of(pr.getPageNumber(), pr.getPageSize()));
    }

    public Flux<GatePay> paysByFIO(String fio, PageRequest of) {
        Flux<GatePay> byClientFioContaining = probBankReactiveRepository.findByClientFioContaining(fio,PageRequest.of(of.getPageNumber(), of.getPageSize()));
        return byClientFioContaining; }

    public Flux<GatePay> paysByParams(String par) {
        return probBankReactiveRepository.findByParamsContaining(par);
    }
}
