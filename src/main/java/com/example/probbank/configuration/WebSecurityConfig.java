package com.example.probbank.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    /*
     * private final AuthenticationManager authenticationManager;
     * 
     * 
     * public WebSecurityConfig(AuthenticationManager authenticationManager) {
     * this.authenticationManager = authenticationManager;
     * }
     * 
     * 
     * @Value("${spring.websecurity.debug:false}")
     * boolean webSecurityDebug;
     * 
     * @Bean
     * public WebSecurityCustomizer webSecurityCustomizer() {
     * return (web) -> web.debug(webSecurityDebug);
     * }
     * 
     * public void configure(WebSecurityConfig web) {
     * 
     * }
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {

        // AuthenticationWebFilter authenticationWebFilter = new
        // AuthenticationWebFilter();

        System.out.println("Поехали!");
        return httpSecurity
                /*
                 * .exceptionHandling()
                 * .authenticationEntryPoint(
                 * (swe, e) ->
                 * Mono.fromRunnable(
                 * () -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
                 * )
                 * )
                 * .accessDeniedHandler(
                 * (swe, e) ->
                 * Mono.fromRunnable(
                 * () -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)
                 * )
                 * )
                 * .and()
                 */
                .csrf().disable()
                .formLogin().and()
                .httpBasic().disable()
                .authorizeExchange()
                .pathMatchers("/", "/login", "/favicon.ico").permitAll()
                .pathMatchers("/controller/**", "/fio/**").hasRole("MONITOR")
                .anyExchange().authenticated()
                .and().authorizeExchange()
                .and()
                .build();
    }
}
