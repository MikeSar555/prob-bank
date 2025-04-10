
package com.example.probbank.controller;

import com.example.probbank.configuration.JwtUtil;
import com.example.probbank.domain.User;
import com.example.probbank.domain.UserRole;
import com.example.probbank.repository.UserReactiveRepo;
import com.example.probbank.service.KafkaLoggingService;
import com.example.probbank.service.UserService;

import reactor.core.publisher.Mono;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;

@RestController
public class UserController {
        private final JwtUtil jwtUtil;
        private final UserReactiveRepo userReactiveRepo;
        @Autowired
        private KafkaLoggingService kafkaLoggingService;

        public UserController(JwtUtil jwtUtil, UserReactiveRepo userReactiveRepo) {
                this.jwtUtil = jwtUtil;
                this.userReactiveRepo = userReactiveRepo;

        }

        @PostMapping("/login")
        public Mono<ResponseEntity<?>> login(@RequestBody User request) {
                System.out.println("Login attempt for user: " + request.getUsername() + ", password: "
                                + request.getPassword());
                kafkaLoggingService.log("Login attempt: " + request.getUsername());

                return userReactiveRepo.findByUsername(request.getUsername())
                                .flatMap(user -> {
                                        if (user.getPassword() != null
                                                        && user.getPassword().equals(request.getPassword())) {
                                                String token = jwtUtil.generateToken(user);
                                                System.out.println("Generated token: " + token);
                                                return Mono.just(ResponseEntity.ok()
                                                                .header(HttpHeaders.AUTHORIZATION, token)
                                                                .body(Map.of("role", user.getRole())));
                                        } else {
                                                return Mono.just(
                                                                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
                                        }
                                })
                                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
        }

        @PostMapping("/auth/service")
        public Mono<ResponseEntity<Map<String, UserRole>>> generateServiceToken() {
                // сторонний сервис получает пользователя с правами MONITOR
                return userReactiveRepo.findByUsername("Мониторинг")
                                .flatMap(user -> {
                                        String token = jwtUtil.generateToken(user);
                                        System.out.println("user: " + user + " token: " + token);
                                        kafkaLoggingService.log("request from service : " + user);
                                        return Mono.just(ResponseEntity.ok()
                                                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                                                        .body(Map.of("role", user.getRole())));
                                })
                                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));

        }

}