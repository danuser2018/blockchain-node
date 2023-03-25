package me.dserrano.blockchain.application.config;

import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Configuration
@Generated
public class DateTimeConfig {
    @Bean
    Supplier<LocalDateTime> nowSupplier() {
        return LocalDateTime::now;
    }

    @Bean
    Supplier<Long> currentTimeMillis() { return System::currentTimeMillis; }
}
