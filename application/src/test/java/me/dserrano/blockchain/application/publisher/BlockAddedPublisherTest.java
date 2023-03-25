package me.dserrano.blockchain.application.publisher;

import me.dserrano.blockchain.application.event.BlockAdded;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@SpringBootTest(classes = {BlockAddedPublisher.class, BlockAddedPublisherTest.TestConsumer.class})
class BlockAddedPublisherTest {
    @Component
    public static class TestConsumer {
        boolean consumed = false;

        @EventListener
        public void consume(BlockAdded blockAdded) {
            consumed = true;
        }
    }

    @Autowired
    private BlockAddedPublisher blockAddedPublisher;

    @Autowired
    private TestConsumer testConsumer;

    @Test
    @DisplayName("Test that when produce a BlockAdded event, it is published")
    void testPublishing() {
        blockAddedPublisher.publishBlockAdded();
        Awaitility.await().atMost(Duration.ofSeconds(10)).untilAsserted(
                () -> Assertions.assertTrue(testConsumer.consumed)
        );
    }
}
