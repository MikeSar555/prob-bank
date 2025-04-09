package com.example.probbank.service;

import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import org.springframework.stereotype.Service;

@Service
public class KafkaLoggingService {
    private final KafkaSender<String, String> kafkaSender;
    private static final String LOG_TOPIC = "prb-payments-logs";

    public KafkaLoggingService(KafkaSender<String, String> kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    public void log(String message) {
        kafkaSender.send(
                Mono.just(SenderRecord.create(
                        LOG_TOPIC,
                        0,
                        System.currentTimeMillis(),
                        String.valueOf(message.hashCode()),
                        message,
                        null)))
                .subscribe();
    }
}