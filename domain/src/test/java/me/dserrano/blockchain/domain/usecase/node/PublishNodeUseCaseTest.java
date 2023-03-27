package me.dserrano.blockchain.domain.usecase.node;

import me.dserrano.blockchain.domain.ports.secondary.NodeEventBus;
import me.dserrano.blockchain.domain.usecase.node.PublishNodeUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static me.dserrano.blockchain.domain.model.NodeMother.node;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = PublishNodeUseCase.class)
class PublishNodeUseCaseTest {
    @MockBean
    private NodeEventBus nodeEventBus;

    @Autowired
    private PublishNodeUseCase publishNodeUseCase;

    @Test
    @DisplayName("Publish node calls the node event bus to publish the node info")
    void testPublishCallsNodeEventBus() {
        var dateTime = LocalDateTime.now();
        publishNodeUseCase.publish(node, dateTime);

        verify(nodeEventBus).publish(node, dateTime);
    }
}
