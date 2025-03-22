package com.example.probbank.repository;

import com.example.probbank.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface UserReactiveRepo extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUsername(String name);
}
