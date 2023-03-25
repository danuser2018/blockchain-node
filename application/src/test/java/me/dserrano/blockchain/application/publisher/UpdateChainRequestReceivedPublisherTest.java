package me.dserrano.blockchain.application.publisher;

import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import me.dserrano.blockchain.application.model.UpdateChainRequestMother;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@SpringBootTest(classes = {
        UpdateChainRequestReceivedPublisher.class,
        UpdateChainRequestReceivedPublisherTest.TestConsumer.class
})
class UpdateChainRequestReceivedPublisherTest {
    @Component
    public static class TestConsumer {
        boolean consumed = false;

        @EventListener
        public void consume(UpdateChainRequestReceived updateChainRequestReceived) {
            consumed = true;
        }
    }

    @Autowired
    private UpdateChainRequestReceivedPublisher updateChainRequestReceivedPublisher;

    @Autowired
    private TestConsumer testConsumer;

    @Test
    @DisplayName("Test that when produce a UpdateChainRequestReceived event, it is published")
    void testPublishing() {
        updateChainRequestReceivedPublisher.publishChainUpdateRequestReceived(
                new UpdateChainRequestReceived(UpdateChainRequestMother.updateChainRequest)
        );

        Awaitility.await().atMost(Duration.ofSeconds(10)).untilAsserted(
                () -> Assertions.assertTrue(testConsumer.consumed)
        );
    }
}
