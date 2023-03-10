package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.model.NodeMother;
import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {NodeQueryServiceImpl.class, NodeQueryServiceImplTest.TestConfig.class})
class NodeQueryServiceImplTest {

    @TestConfiguration
    public static class TestConfig {
        @Bean
        Node selfNode() {
            return NodeMother.node;
        }
    }

    @Autowired
    NodeQueryService nodeQueryService;

    @Test
    @DisplayName("When call to getSelfNode the selfNode is returned")
    void testReturnSelfNode() {
        var result = nodeQueryService.getSelfNode();

        assertEquals(NodeMother.node, result);
    }

}