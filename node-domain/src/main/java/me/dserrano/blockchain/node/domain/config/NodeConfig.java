package me.dserrano.blockchain.node.domain.config;

import me.dserrano.blockchain.node.domain.model.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Supplier;

@Configuration
public class NodeConfig {
    @Bean
    Node selfNode(Supplier<UUID> uuidSupplier,
                  @Value("${blockchain.nodes.self.host}") String selfNodeHost,
                  @Value("${blockchain.nodes.self.port}") int selfNodePort
    ) {
        return Node.builder()
                .id(uuidSupplier.get().toString())
                .host(selfNodeHost)
                .port(selfNodePort)
                .build();
    }
}
