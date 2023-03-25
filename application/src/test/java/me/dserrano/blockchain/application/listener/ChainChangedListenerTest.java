package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.event.BlockAdded;
import me.dserrano.blockchain.application.event.ChainUpdated;
import me.dserrano.blockchain.application.handler.NotifyChangesHandler;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.function.Supplier;

import static java.time.Duration.ofSeconds;

@SpringBootTest(classes = ChainChangedListener.class)
public class ChainChangedListenerTest {
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("blockchain.nodes.self.publish-rate-ms", () -> 1000L);
    }

    @MockBean
    private NotifyChangesHandler notifyChangesHandler;
    @MockBean
    private Supplier<Long> currentTimeMillisSupplier;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    @DisplayName("Test that if enough time has passed then the chain updated event is published")
    @DirtiesContext
    public void testChangedEventIsPublished() {
        Mockito.when(currentTimeMillisSupplier.get()).thenReturn(1010L);
        applicationEventPublisher.publishEvent(ChainUpdated.builder().build());
        Awaitility.await().atMost(ofSeconds(10)).untilAsserted(
                () -> Mockito.verify(notifyChangesHandler).notifyChanges()
        );
    }

    @Test
    @DisplayName("Test that if enough time has passed then the block added event is published")
    @DirtiesContext
    public void testBlockAddedEventIsPublished() {
        Mockito.when(currentTimeMillisSupplier.get()).thenReturn(1010L);
        applicationEventPublisher.publishEvent(BlockAdded.builder().build());
        Awaitility.await().atMost(ofSeconds(10)).untilAsserted(
                () -> Mockito.verify(notifyChangesHandler).notifyChanges()
        );
    }

    @Test
    @DisplayName("Test that if not enough time has passed then the block added event is not published")
    @DirtiesContext
    public void testBlockAddedEventIsNotPublished() {
        Mockito.when(currentTimeMillisSupplier.get()).thenReturn(500L);
        applicationEventPublisher.publishEvent(BlockAdded.builder().build());
        Awaitility.await().during(ofSeconds(5)).atMost(ofSeconds(6)).untilAsserted(
                () -> Mockito.verifyNoInteractions(notifyChangesHandler)
        );
    }

    @Test
    @DisplayName("Test that if not enough time has passed then the chain updated event is not published")
    @DirtiesContext
    public void testChainUpdatedEventIsNotPublished() {
        Mockito.when(currentTimeMillisSupplier.get()).thenReturn(500L);
        applicationEventPublisher.publishEvent(ChainUpdated.builder().build());
        Awaitility.await().during(ofSeconds(5)).atMost(ofSeconds(6)).untilAsserted(
                () -> Mockito.verifyNoInteractions(notifyChangesHandler)
        );
    }
}
