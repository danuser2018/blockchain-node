package me.dserrano.blockchain.application.node;

import lombok.Data;
import me.dserrano.blockchain.application.node.command.PublishNodeCommand;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static java.time.Duration.ofMinutes;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        ScheduledNodePublisher.class,
        ScheduledNodePublisherTest.TestConfig.class,
        ScheduledNodePublisherTest.TestListener.class
})
@ExtendWith(MockitoExtension.class)
class ScheduledNodePublisherTest {
    private static final LocalDateTime now = LocalDateTime.now();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("blockchain.nodes.self.publish-rate-ms", () -> 60000);
    }

    @TestConfiguration
    public static class TestConfig {
        @Bean
        Supplier<LocalDateTime> nowSupplier() {
            return () -> now;
        }

        @Bean
        NodeQueryService nodeQueryService() {
            var node = Node.builder()
                    .id("6405c233-3208-428d-ad8e-9cf17f164d85")
                    .host("localhost")
                    .port(8080)
                    .build();

            NodeQueryService nodeQueryService = mock(NodeQueryService.class);
            when(nodeQueryService.getSelfNode()).thenReturn(node);

            return nodeQueryService;
        }
    }

    @Data
    @Component
    public static class TestListener {
        private boolean consumed = false;
        private PublishNodeCommand payload;

        @EventListener(PublishNodeCommand.class)
        public void consumeEvent(PublishNodeCommand command) {
            payload = command;
            consumed = true;
        }
    }

    @Autowired
    TestListener testListener;

    @Test
    @DisplayName("Before 1 minute nodePublicationService is invoked for the first time")
    void testNodePublicationServiceInvokedBefore1Minute() {
        Awaitility.await()
                .atMost(ofMinutes(1))
                .untilAsserted(() -> Assertions.assertTrue(testListener.isConsumed()));

        var result = testListener.getPayload();

        Assertions.assertEquals("6405c233-3208-428d-ad8e-9cf17f164d85", result.node().id());
        Assertions.assertEquals("localhost", result.node().host());
        Assertions.assertEquals(8080, result.node().port());
        Assertions.assertEquals(now, result.dateTime());
    }
}
