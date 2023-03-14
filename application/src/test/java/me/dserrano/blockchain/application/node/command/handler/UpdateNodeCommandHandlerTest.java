package me.dserrano.blockchain.application.node.command.handler;

import me.dserrano.blockchain.application.node.command.PublishNodeCommand;
import me.dserrano.blockchain.application.node.command.UpdateNodeCommand;
import me.dserrano.blockchain.application.node.config.AsyncEventConfig;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.PublishNodeService;
import me.dserrano.blockchain.domain.node.ports.primary.UpdateNodeService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

import static java.time.Duration.ofMinutes;

@SpringBootTest(classes = {UpdateNodeCommandHandler.class, AsyncEventConfig.class})
@ExtendWith(MockitoExtension.class)
public class UpdateNodeCommandHandlerTest {
    @MockBean
    private UpdateNodeService updateNodeService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    @DisplayName("PublishNode command is consumed and passed to the service")
    void consumeUpdateNodeCommand() {
        var node = Node.builder()
                .id("6405c233-3208-428d-ad8e-9cf17f164d85")
                .host("localhost")
                .port(8080)
                .build();

        var dateTime = LocalDateTime.now();

        applicationEventPublisher.publishEvent(UpdateNodeCommand.builder()
                .node(node)
                .dateTime(dateTime)
                .build());

        Awaitility.await().atMost(ofMinutes(1))
                .untilAsserted(() -> Mockito.verify(updateNodeService).update(node, dateTime));
    }
}
