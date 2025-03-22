package com.example.probbank.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;

import com.example.probbank.domain.GatePay;
import com.example.probbank.service.GatePayService;

import reactor.core.publisher.Flux;

@EnableR2dbcRepositories
@RestController()
// @RequestMapping("")
public class ProbBankController {
    @Autowired
    private final GatePayService gatePayService;

    private static final int DELAY_PER_ITEM_MS = 100;

    public ProbBankController(GatePayService gatePayService) {
        this.gatePayService = gatePayService;
    }

    @GetMapping(path = "/controller/paysByParam", produces = "text/event-stream")
    public Flux<GatePay> getPaysByParams(@RequestParam(value = "param", defaultValue = "www") String par,
            final @RequestParam(name = "page") int page,
            final @RequestParam(name = "size") int size) {
        Flux<GatePay> payFlux = gatePayService.paysByParams(par, PageRequest.of(page - 1, size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
        ;
        return payFlux;
    }

    @GetMapping(path = "/controller", produces = "text/event-stream")
    public Flux<GatePay> gatePay902717(@RequestParam(defaultValue = "902717") String param,
            final @RequestParam(name = "page") int page,
            final @RequestParam(name = "size") int size) {
        Flux<GatePay> payFlux = gatePayService.paysByParams(param, PageRequest.of(page - 1, size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
        ;
        return payFlux;
    }

    @GetMapping(path = "/fio", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // "text/event-stream")
    public Flux<GatePay> findByFio(@RequestParam(value = "fio") String fio,
            final @RequestParam(name = "page") int page,
            final @RequestParam(name = "size") int size) {
        Flux<GatePay> payByFio = gatePayService.paysByFIO(fio,
                PageRequest.of(page - 1, size)).delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
        return payByFio;
    }

    @GetMapping(path = "/findAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<GatePay> findAll() {
        // @ModelAttribute
        return null;

    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    // @PostMapping("/login")
    // public RedirectView login() {
    // return new RedirectView("http://localhost:3000"); // URL вашего
    // // React-приложения
    // }

    /*
     * @GetMapping("")
     * public Mono<String> greet(Mono<Principal> principal) {
     * return principal
     * .map(Principal::getName)
     * .map(name -> String.format("Hello, %s", name));
     * }
     */

}
