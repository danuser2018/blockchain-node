package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.event.BlockAdded;
import me.dserrano.blockchain.application.handler.UpdateChainHandler;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static java.time.Duration.ofSeconds;

@SpringBootTest(classes = BlockAddedListener.class)
public class BlockAddedListenerTest {
    @MockBean
    private UpdateChainHandler updateChainHandler;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    @DisplayName("Test that BlockAdded event is consumed")
    public void testBlockAddedEventIsConsumed() {
        applicationEventPublisher.publishEvent(BlockAdded.builder().build());
        Awaitility.await().atMost(ofSeconds(10)).untilAsserted(
                () -> Mockito.verify(updateChainHandler).updateChain()
        );
    }
}
