package me.dserrano.blockchain.domain.usecase.node;

import me.dserrano.blockchain.domain.model.NodeMother;
import me.dserrano.blockchain.domain.model.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ObtainSelfNodeUseCase.class, ObtainSelfNodeUseCaseTest.TestConfig.class})
class ObtainSelfNodeUseCaseTest {

    @TestConfiguration
    public static class TestConfig {
        @Bean
        Node selfNode() {
            return NodeMother.node;
        }
    }

    @Autowired
    ObtainSelfNodeUseCase obtainSelfNodeUseCase;

    @Test
    @DisplayName("When call to getSelfNode the selfNode is returned")
    void testReturnSelfNode() {
        var result = obtainSelfNodeUseCase.getSelfNode();

        assertEquals(NodeMother.node, result);
    }

}