package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.event.ChainUpdated;
import me.dserrano.blockchain.application.handler.AddBlockHandler;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static java.time.Duration.ofSeconds;

@SpringBootTest(classes = ChainUpdatedListener.class)
public class ChainUpdatedListenerTest {
    @MockBean
    private AddBlockHandler addBlockHandler;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    @DisplayName("Test that ChainUpdated event is consumed")
    public void testChainUpdatedEventIsConsumed() {
        applicationEventPublisher.publishEvent(ChainUpdated.builder().build());
        Awaitility.await().atMost(ofSeconds(10)).untilAsserted(
                () -> Mockito.verify(addBlockHandler).addBlock()
        );
    }
}
