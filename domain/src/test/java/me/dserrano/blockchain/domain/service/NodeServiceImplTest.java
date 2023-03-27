package me.dserrano.blockchain.domain.service;

import me.dserrano.blockchain.domain.usecase.node.ObtainSelfNodeUseCase;
import me.dserrano.blockchain.domain.usecase.node.PublishNodeUseCase;
import me.dserrano.blockchain.domain.usecase.node.UpdateNodeUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static me.dserrano.blockchain.domain.model.NodeMother.anotherNode;
import static me.dserrano.blockchain.domain.model.NodeMother.node;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = NodeServiceImpl.class)
class NodeServiceImplTest {
    @MockBean
    private ObtainSelfNodeUseCase obtainSelfNodeUseCase;

    @MockBean
    private PublishNodeUseCase publishNodeUseCase;

    @MockBean
    private UpdateNodeUseCase updateNodeUseCase;

    @Autowired
    private NodeServiceImpl nodeService;

    @Test
    @DisplayName("getSelfNode returns the selfNode")
    void testGetSelfNode() {
        when(obtainSelfNodeUseCase.getSelfNode()).thenReturn(node);
        var result = nodeService.getSelfNode();
        Assertions.assertEquals(node, result);
    }

    @Test
    @DisplayName("publishSelfNode publish the info of the self node")
    void testPublishSelfNode() {
        var dateTime = LocalDateTime.now();
        when(obtainSelfNodeUseCase.getSelfNode()).thenReturn(node);
        nodeService.publishSelfNode(dateTime);
        verify(publishNodeUseCase).publish(node, dateTime);
    }

    @Test
    @DisplayName("storeOtherNode stores other node info")
    void testStoreOtherNodeInfo() {
        var dateTime = LocalDateTime.now();
        when(obtainSelfNodeUseCase.getSelfNode()).thenReturn(node);
        nodeService.storeOtherNode(anotherNode, dateTime);
        verify(updateNodeUseCase).update(anotherNode, dateTime);
    }

    @Test
    @DisplayName("storeOtherNode doesn't store nothing if node is selfNode")
    @DirtiesContext
    void testStoreOtherNodeWithSelfNode() {
        var dateTime = LocalDateTime.now();
        when(obtainSelfNodeUseCase.getSelfNode()).thenReturn(node);
        nodeService.storeOtherNode(node, dateTime);
        verifyNoInteractions(updateNodeUseCase);
    }
}
