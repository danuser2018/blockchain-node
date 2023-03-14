package me.dserrano.blockchain.infra.kafka.node.consumer;

import lombok.Data;
import me.dserrano.blockchain.application.node.command.UpdateNodeCommand;
import me.dserrano.blockchain.infra.kafka.node.config.NodeTopicConfig;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapperImpl;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static java.time.Duration.ofMinutes;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.*;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.dateTime;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        NodeEventConsumer.class,
        NodeTopicConfig.class,
        KafkaAutoConfiguration.class,
        NodeEventMapperImpl.class,
        NodeEventConsumerIT.TestListener.class
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

    @Data
    @Component
    public static class TestListener {
        private boolean consumed = false;
        private UpdateNodeCommand payload;

        @EventListener(UpdateNodeCommand.class)
        public void consumeEvent(UpdateNodeCommand command) {
            payload = command;
            consumed = true;
        }
    }

    @Autowired
    TestListener testListener;

    @Autowired
    private KafkaTemplate<String, NodeEvent> kafkaTemplate;

    @Test
    @DisplayName("Consume successfully")
    void testConsumeSuccessfully() {
        kafkaTemplate.send("node-topic", nodeEvent.id(), nodeEvent);

        Awaitility.await()
                .atMost(ofMinutes(1))
                .untilAsserted(() -> Assertions.assertTrue(testListener.isConsumed()));

        var result = testListener.getPayload();

        Assertions.assertEquals(uuid, result.node().id());
        Assertions.assertEquals(host, result.node().host());
        Assertions.assertEquals(port, result.node().port());
        Assertions.assertEquals(dateTime, result.dateTime());
    }
}
