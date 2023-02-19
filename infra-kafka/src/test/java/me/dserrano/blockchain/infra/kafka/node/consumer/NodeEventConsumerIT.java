package me.dserrano.blockchain.infra.kafka.node.consumer;

import me.dserrano.blockchain.infra.kafka.node.config.NodeTopicConfig;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import me.dserrano.blockchain.node.domain.ports.primary.NodeCommandService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.nodeEvent;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.updateNodeCommand;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        NodeEventConsumer.class,
        NodeTopicConfig.class,
        KafkaAutoConfiguration.class,
        NodeEventMapper.class
})
@Testcontainers
@DirtiesContext
class NodeEventConsumerIT {
    @Container
    static final KafkaContainer kafkaContainer = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:6.2.1")
    );

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("blockchain.nodes.topic.name", () -> "node-topic");
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("spring.kafka.producer.key-serializer", () -> "org.apache.kafka.common.serialization.StringSerializer");
        registry.add("spring.kafka.producer.value-serializer", () -> "org.springframework.kafka.support.serializer.JsonSerializer");
        registry.add("spring.kafka.consumer.group-id", () -> "group-id");
        registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
        registry.add("spring.kafka.consumer.key-deserializer", () -> "org.apache.kafka.common.serialization.StringDeserializer");
        registry.add("spring.kafka.consumer.value-deserializer", () -> "org.springframework.kafka.support.serializer.JsonDeserializer");
        registry.add("spring.kafka.consumer.properties.spring.json.trusted.packages", () -> "*");
    }

    @Autowired
    private KafkaTemplate<String, NodeEvent> kafkaTemplate;

    @MockBean
    private NodeEventMapper nodeEventMapper;

    @MockBean
    private NodeCommandService nodeCommandService;

    @Test
    @DisplayName("Consume successfully")
    void testConsumeSuccessfully() {
        when(nodeEventMapper.toUpdateNodeCommand(nodeEvent)).thenReturn(updateNodeCommand);

        kafkaTemplate.send("node-topic", nodeEvent.id(), nodeEvent);

        Awaitility.await()
                .atMost(Duration.ofMinutes(1))
                .untilAsserted(() -> verify(nodeCommandService).process(updateNodeCommand));
    }
}
