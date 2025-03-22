package com.example.probbank.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity, JwtAuthFilter jwtAuthFilter) {
        System.out.println("зашли в WebSecurityConfig строка 26... ");
        return httpSecurity
                .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:3000");
                    config.addAllowedMethod("*");
                    config.addAllowedHeader("*");
                    config.addExposedHeader("Authorization");
                    config.addAllowedHeader("Authorization");
                    config.setAllowCredentials(true);
                    config.addAllowedMethod("*"); // Разрешить все методы (GET, POST, OPTIONS и т.д.)
                    config.addAllowedHeader("Authorization"); // Явное разрешение заголовка
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/login", "/static/**").permitAll()
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .pathMatchers("/controller/**", "/fio/**", "controller/paysByParam/**").hasRole("MONITOR") // permitAll()//
                // .hasRole("MONITOR")
                .anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable() // Отключите форму входа Spring
                .build();
    }

}
