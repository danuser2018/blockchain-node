package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.domain.ports.primary.NodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static me.dserrano.blockchain.application.model.UpdateChainRequestMother.updateChainRequest;

@SpringBootTest(classes = UpdateChainRequestHandler.class)
class UpdateChainRequestHandlerTest {
    @MockBean
    private NodeService nodeService;

    @Autowired
    private UpdateChainRequestHandler updateChainRequestHandler;

    @Test
    @DisplayName("Test that the update chain request is processed")
    void testUpdateChainRequestIsProcessed() {
        updateChainRequestHandler.processUpdateChainRequest(updateChainRequest);
        Mockito.verify(nodeService).storeOtherNode(updateChainRequest.node(), updateChainRequest.dateTime());
    }
}
