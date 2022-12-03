package com.example.probbank.repository;

import com.example.probbank.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserReactiveRepo extends ReactiveCrudRepository<User, Long> {
    Mono<User>  findByUsername(String name);
}
