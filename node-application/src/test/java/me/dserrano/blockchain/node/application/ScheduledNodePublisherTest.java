package me.dserrano.blockchain.node.application;

import me.dserrano.blockchain.node.domain.model.Node;
import me.dserrano.blockchain.node.domain.model.command.PublishNodeCommand;
import me.dserrano.blockchain.node.domain.ports.primary.NodeCommandService;
import me.dserrano.blockchain.node.domain.ports.primary.NodeQueryService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static java.time.Duration.ofMinutes;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ScheduledNodePublisher.class, ScheduledNodePublisherTest.TestConfig.class})
@ExtendWith(MockitoExtension.class)
public class ScheduledNodePublisherTest {
    private static final LocalDateTime now = LocalDateTime.now();
    @MockBean
    private NodeCommandService nodeCommandService;
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

    @Captor
    private ArgumentCaptor<PublishNodeCommand> commandCaptor;

    @Test
    @DisplayName("Before 5 minutes nodePublicationService is invoked for the first time")
    void testNodePublicationServiceInvokedBefore5Minutes() {
        Awaitility.await()
                .atMost(ofMinutes(5))
                .untilAsserted(() -> verify(nodeCommandService).process(commandCaptor.capture()));

        var result = commandCaptor.getValue();

        Assertions.assertEquals("6405c233-3208-428d-ad8e-9cf17f164d85", result.node().id());
        Assertions.assertEquals("localhost", result.node().host());
        Assertions.assertEquals(8080, result.node().port());
        Assertions.assertEquals(now, result.dateTime());
    }
}
