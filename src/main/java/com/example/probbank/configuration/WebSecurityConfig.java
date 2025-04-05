package com.example.probbank.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.server.session.DefaultWebSessionManager;
import org.springframework.web.server.session.WebSessionManager;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public WebSessionManager webSessionManager() {
        return new DefaultWebSessionManager();
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity, JwtAuthFilter jwtAuthFilter) {
        System.out.println("зашли в WebSecurityConfig строка 26... ");
        return httpSecurity
                .securityContextRepository(WebSessionServerSecurityContextRepository()) // Используем сессии
                .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3003"));
                    config.setAllowedMethods(Arrays.asList("*"));
                    config.setAllowedHeaders(Arrays.asList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization")); // Клиент должен видеть этот заголовок
                    config.addExposedHeader("Authorization");
                    config.addAllowedHeader("Authorization");
                    // config.setAllowCredentials(true);
                    // config.addAllowedMethod("*"); // Разрешить все методы (GET, POST, OPTIONS и
                    // т.д.)
                    // config.addAllowedHeader("Authorization"); // Явное разрешение заголовка
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/login", "/auth/service", "/static/**",
                        "/controller/paysByParamAll/**")
                // "/controller/paysByParamAll/**")
                .permitAll()
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .pathMatchers("/controller/**", "/fio/**", "/controller/paysByParam/**")
                // "/controller/paysByParamAll/**")
                .hasRole("MONITOR") // permitAll()//
                // .hasRole("MONITOR")
                .anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable() // Отключите форму входа Spring
                .build();
    }

    @Bean
    public ServerSecurityContextRepository WebSessionServerSecurityContextRepository() {
        return new WebSessionServerSecurityContextRepository();
    }

}
