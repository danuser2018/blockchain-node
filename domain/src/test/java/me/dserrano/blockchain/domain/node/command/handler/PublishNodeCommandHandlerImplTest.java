package me.dserrano.blockchain.domain.node.command.handler;

import me.dserrano.blockchain.domain.node.ports.secondary.NodeEventBus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static me.dserrano.blockchain.domain.node.model.NodeMother.node;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = PublishNodeCommandHandlerImpl.class)
class PublishNodeCommandHandlerImplTest {
    @MockBean
    private NodeEventBus nodeEventBus;

    @Autowired
    private PublishNodeCommandHandler publishNodeCommandHandler;

    @Test
    @DisplayName("Publish node command handler calls the node event bus to publish the node info")
    void testPublishCommandHandlerCallsNodeEventBus() {
        var dateTime = LocalDateTime.now();
        publishNodeCommandHandler.publish(node, dateTime);

        verify(nodeEventBus).publish(node, dateTime);
    }
}
