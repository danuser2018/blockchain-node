package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import me.dserrano.blockchain.domain.node.ports.primary.UpdateNodeService;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static me.dserrano.blockchain.domain.node.model.NodeMother.anotherNode;
import static me.dserrano.blockchain.domain.node.model.NodeMother.node;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UpdateNodeUseCase.class)
class UpdateNodeUseCaseTest {

    @MockBean
    NodeQueryService nodeQueryService;

    @MockBean
    NodeDao nodeDao;

    @Autowired
    UpdateNodeService updateNodeService;

    @Test
    @DisplayName("When node is the self node then node dao is not invoked")
    void testNodeIsSelfNode() {
        var dateTime = LocalDateTime.now();

        when(nodeQueryService.getSelfNode()).thenReturn(node);

        updateNodeService.update(node, dateTime);

        verifyNoInteractions(nodeDao);
    }

    @Test
    @DisplayName("When node is not the self node then node dao is invoked")
    void testNodeIsNotSelfNode() {
        var dateTime = LocalDateTime.now();

        when(nodeQueryService.getSelfNode()).thenReturn(node);

        updateNodeService.update(anotherNode, dateTime);

        verify(nodeDao).saveNodeInfo(anotherNode, dateTime);
    }
}