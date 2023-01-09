package me.dserrano.blockchain.infra.kafka.node.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = NodeTopicConfig.class)
class NodeTopicConfigTest {
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("blockchain.nodes.topic.name", () -> "node-topic");
    }

    @Autowired
    private NewTopic nodeTopic;

    @Autowired
    private String nodeTopicName;

    @Test
    @DisplayName("Values for the new topic are recovered properly")
    void testNewTopic() {
        assertEquals("node-topic", nodeTopic.name());
        assertEquals(3, nodeTopic.numPartitions());
        assertEquals((short) 1, nodeTopic.replicationFactor());
    }

    @Test
    @DisplayName("There is a bean with the name of the topic")
    void testTopicName() {
        assertEquals("node-topic", nodeTopicName);
    }
}