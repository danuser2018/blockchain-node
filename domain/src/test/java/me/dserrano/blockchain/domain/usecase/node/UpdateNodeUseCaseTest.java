package me.dserrano.blockchain.domain.usecase.node;

import me.dserrano.blockchain.domain.ports.secondary.NodeDao;
import me.dserrano.blockchain.domain.usecase.node.UpdateNodeUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static me.dserrano.blockchain.domain.model.NodeMother.node;
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