package me.dserrano.blockchain.node.domain.config;

import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Supplier;

@Configuration
@Generated
public class UuidConfig {
    @Bean
    Supplier<UUID> uuidSupplier() {
        return UUID::randomUUID;
    }
}
