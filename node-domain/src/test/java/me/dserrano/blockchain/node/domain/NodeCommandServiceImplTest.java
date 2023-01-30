package me.dserrano.blockchain.node.domain;

import me.dserrano.blockchain.node.domain.command.handler.PublishNodeCommandHandler;
import me.dserrano.blockchain.node.domain.model.NodeMother;
import me.dserrano.blockchain.node.domain.model.command.PublishNodeCommand;
import me.dserrano.blockchain.node.domain.ports.primary.NodeCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = NodeCommandServiceImpl.class)
@ExtendWith(MockitoExtension.class)
class NodeCommandServiceImplTest {
    @MockBean
    private PublishNodeCommandHandler publishNodeCommandHandler;

    @Autowired
    private NodeCommandService nodeCommandService;

    @Test
    @DisplayName("Given a PublishNodeCommand then publishNodeCommandHandler is invoked")
    void testPublishNodeCommandHandlerIsProcessed() {
        var now = LocalDateTime.now();
        var command = PublishNodeCommand.builder().node(NodeMother.node).dateTime(now).build();

        nodeCommandService.process(command);

        verify(publishNodeCommandHandler).publish(command.node(), command.dateTime());
    }

    @Test
    @DisplayName("Given any other type then an IllegalArgumentException is thrown")
    void testIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> nodeCommandService.process("A string")
        );
    }
}