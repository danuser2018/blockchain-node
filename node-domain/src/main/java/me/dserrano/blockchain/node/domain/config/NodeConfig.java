package me.dserrano.blockchain.node.domain.config;

import me.dserrano.blockchain.node.domain.model.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NodeConfig {
    @Bean
    Node selfNode(@Value("${blockchain.nodes.self.id}") String selfNodeId,
                  @Value("${blockchain.nodes.self.host}") String selfNodeHost,
                  @Value("${blockchain.nodes.self.port}") int selfNodePort
    ) {
        return Node.builder()
                .id(selfNodeId)
                .host(selfNodeHost)
                .port(selfNodePort)
                .build();
    }
}
