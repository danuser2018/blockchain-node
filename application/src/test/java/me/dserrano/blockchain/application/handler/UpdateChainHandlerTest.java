package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.application.publisher.ChainUpdatedPublisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = UpdateChainHandler.class)
public class UpdateChainHandlerTest {
    @MockBean
    private ChainUpdatedPublisher chainUpdatedPublisher;
    @Autowired
    private UpdateChainHandler updateChainHandler;

    @Test
    @DisplayName("Test notification is created")
    public void testChainIsUpdated() {
        updateChainHandler.updateChain();
        Mockito.verify(chainUpdatedPublisher).publishChainUpdated();
    }
}
