package me.dserrano.blockchain.infra.kafka.node.producer;

import lombok.Data;
import me.dserrano.blockchain.infra.kafka.node.config.LoggingNodeEventProducerActionConfig;
import me.dserrano.blockchain.infra.kafka.node.config.NodeTopicConfig;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.nodeEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {
        NodeEventProducer.class,
        NodeTopicConfig.class,
        LoggingNodeEventProducerActionConfig.class,
        KafkaAutoConfiguration.class,
        NodeEventProducerIT.TestConsumer.class
})
@Testcontainers
@DirtiesContext
class NodeEventProducerIT {
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
    public static class TestConsumer {
        private boolean consumed = false;
        private NodeEvent payload;

        @KafkaListener(topics = "${blockchain.nodes.topic.name}")
        public void receive(ConsumerRecord<String, NodeEvent> record) {
            payload = record.value();
            consumed = true;
        }
    }

    @Autowired
    private NodeEventProducer nodeEventProducer;

    @Autowired
    private TestConsumer consumer;

    @Test
    @DisplayName("Produce in kafka")
    void produceInKafka() {
        nodeEventProducer.produce(nodeEvent);
        Awaitility.await()
                .atMost(Duration.ofMinutes(1))
                .untilAsserted(() -> assertTrue(consumer.isConsumed()));

        assertEquals(nodeEvent, consumer.getPayload());
    }
}
