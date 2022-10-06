package com.example.probbank.controller;

import com.example.probbank.domain.GatePay;
import com.example.probbank.service.GatePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

//@EnableR2dbcRepositories
@RestController
public class ProbBankController {
    @Autowired
    private  final  GatePayService gatePayService;

    public ProbBankController(GatePayService gatePayService) {
        this.gatePayService = gatePayService;
    }


    @GetMapping(path = "/paysByParam", produces = "text/event-stream")
    public Flux<GatePay> getPaysByParams(@RequestParam(value = "param", defaultValue = "www") String par){
        Flux<GatePay> payFlux = gatePayService.paysByParams(par);
        return payFlux;
    }
}
