package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.application.publisher.BlockAddedPublisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = AddBlockHandler.class)
public class AddBlockHandlerTest {
    @MockBean
    private BlockAddedPublisher blockAddedPublisher;

    @Autowired
    private AddBlockHandler addBlockHandler;

    @Test
    @DisplayName("Test an event is published")
    public void testBlockIsAdded() {
        addBlockHandler.addBlock();
        Mockito.verify(blockAddedPublisher).publishBlockAdded();
    }
}
