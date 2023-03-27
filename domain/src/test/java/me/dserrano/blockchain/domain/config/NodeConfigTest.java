package me.dserrano.blockchain.domain.config;

import me.dserrano.blockchain.domain.model.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = NodeConfig.class)
class NodeConfigTest {
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("blockchain.nodes.self.id", () -> "c775a4d0-52a0-4a8c-b696-1c537e2a75ea");
        registry.add("blockchain.nodes.self.host", () -> "localhost");
        registry.add("blockchain.nodes.self.port", () -> 8080);
    }

    @Autowired
    Node selfNode;

    @Test
    @DisplayName("selfNode is a Node with configured id, host and port")
    void testSelfNode() {
        assertEquals("c775a4d0-52a0-4a8c-b696-1c537e2a75ea", selfNode.id());
        assertEquals("localhost", selfNode.host());
        assertEquals(8080, selfNode.port());
    }
}
