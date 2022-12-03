package com.example.probbank.service;

import com.example.probbank.domain.User;
import com.example.probbank.repository.UserReactiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class UserService  implements ReactiveUserDetailsService {

    @Autowired
    private final UserReactiveRepo userReactiveRepo;

    public UserService(UserReactiveRepo userReactiveRepo) {
        this.userReactiveRepo = userReactiveRepo;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
/*        System.out.println("service...  "+ username);
        Mono<User> userMono = userReactiveRepo.findByUsername(username);
        System.out.println("userMono "+userMono);
        Mono<UserDetails> userDetails = userMono.cast(UserDetails.class);
        UserDetails user =  userDetails.share().block();
        System.out.println("userDetails "+user.getUsername()+"  password "+ user.getPassword()+"  ");

        System.out.println("user " + user.getUsername()+" password "+ user.getPassword()+" role ");*/

        Mono<User> userMono = userReactiveRepo.findByUsername(username);
//      User user = userMono.toProcessor().block();
        userMono.subscribe(
                value -> System.out.println(value),
                error -> error.printStackTrace(),
                () -> System.out.println("нет значения")
        );
        return  userMono
                .cast(UserDetails.class);
    }
}
