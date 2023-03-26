package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.ports.primary.NodeService;
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
    private NodeDao nodeDao;

    @Autowired
    private UpdateNodeUseCase updateNodeUseCase;

    @Test
    @DisplayName("Update node calls the node dao to store the info")
    void testUpdateCallsNodeDao() {
        var dateTime = LocalDateTime.now();
        updateNodeUseCase.update(node, dateTime);

        verify(nodeDao).saveNodeInfo(node, dateTime);
    }
}