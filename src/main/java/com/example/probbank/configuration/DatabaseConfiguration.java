package com.example.probbank.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "mysql")
                .option(HOST, "localhost")
                .option(USER, "root")
                .option(PASSWORD,"2222")
                .option(PORT, 3306)
                .option(DATABASE,"payment_gate")
                .option(Option.valueOf("useUnicode"),true)
        //        .option(Option.valueOf("useJDBCCompliantTimezoneShift"),true)
                .build();
        ConnectionFactory connectionFactory = ConnectionFactories.get(options);
/*        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);

        Flux<GatePay> l = template.select(GatePay.class).all();
//        Flux<GatePay> l = template.select(GatePay.class).matching(query(where("params").like("\\d9027172340"))).all();
        List<GatePay>  all = l.collectList().block();
        System.out.println("Всего записей с параметром 9027172340 "+ all.size());
*/
        return  connectionFactory;
    }
}
