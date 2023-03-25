package me.dserrano.blockchain.application.publisher;

import me.dserrano.blockchain.application.event.ChainUpdated;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@SpringBootTest(classes = {ChainUpdatedPublisher.class, ChainUpdatedPublisherTest.TestConsumer.class})
class ChainUpdatedPublisherTest {
    @Component
    public static class TestConsumer {
        boolean consumed = false;

        @EventListener
        public void consume(ChainUpdated chainUpdated) {
            consumed = true;
        }
    }

    @Autowired
    private ChainUpdatedPublisher chainUpdatedPublisher;

    @Autowired
    private TestConsumer testConsumer;

    @Test
    @DisplayName("Test that when produce a ChainUpdated event, it is published")
    void testPublishing() {
        chainUpdatedPublisher.publishChainUpdated();
        Awaitility.await().atMost(Duration.ofSeconds(10)).untilAsserted(
                () -> Assertions.assertTrue(testConsumer.consumed)
        );
    }

}
