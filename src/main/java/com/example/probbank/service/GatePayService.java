package com.example.probbank.service;

import com.example.probbank.domain.GatePay;
import com.example.probbank.repository.ProbBankReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GatePayService {

    private final ProbBankReactiveRepository probBankReactiveRepository;

    @Autowired
    public GatePayService(ProbBankReactiveRepository probBankReactiveRepository) {
        this.probBankReactiveRepository = probBankReactiveRepository;
    }

    public Flux<GatePay> paysByParams(String param){
        return probBankReactiveRepository.findByParamsContaining(param);
    }
}
