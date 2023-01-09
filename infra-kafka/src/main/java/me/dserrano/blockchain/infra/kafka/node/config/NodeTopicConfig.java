package me.dserrano.blockchain.infra.kafka.node.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NodeTopicConfig {
    @Value("${blockchain.nodes.topic.name}")
    private String nodeTopicName;

    @Bean
    public String nodeTopicName() {
        return nodeTopicName;
    }

    @Bean
    public NewTopic nodeTopic() {
        return new NewTopic(nodeTopicName, 3, (short) 1);
    }
}
