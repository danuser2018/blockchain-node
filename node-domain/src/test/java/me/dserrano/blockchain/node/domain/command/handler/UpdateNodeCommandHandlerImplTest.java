package me.dserrano.blockchain.node.domain.command.handler;

import me.dserrano.blockchain.node.domain.ports.primary.NodeQueryService;
import me.dserrano.blockchain.node.domain.ports.secondary.NodeDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static me.dserrano.blockchain.node.domain.model.NodeMother.anotherNode;
import static me.dserrano.blockchain.node.domain.model.NodeMother.node;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UpdateNodeCommandHandlerImpl.class)
class UpdateNodeCommandHandlerImplTest {

    @MockBean
    NodeQueryService nodeQueryService;

    @MockBean
    NodeDao nodeDao;

    @Autowired
    UpdateNodeCommandHandler updateNodeCommandHandler;

    @Test
    @DisplayName("When node is the self node then node dao is not invoked")
    void testNodeIsSelfNode() {
        var dateTime = LocalDateTime.now();

        when(nodeQueryService.getSelfNode()).thenReturn(node);

        updateNodeCommandHandler.update(node, dateTime);

        verifyNoInteractions(nodeDao);
    }

    @Test
    @DisplayName("When node is not the self node then node dao is invoked")
    void testNodeIsNotSelfNode() {
        var dateTime = LocalDateTime.now();

        when(nodeQueryService.getSelfNode()).thenReturn(node);

        updateNodeCommandHandler.update(anotherNode, dateTime);

        verify(nodeDao).saveNodeInfo(anotherNode, dateTime);
    }
}