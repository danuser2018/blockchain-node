package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.handler.UpdateChainHandler;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.test.annotation.DirtiesContext;

import static java.time.Duration.ofSeconds;

@SpringBootTest(classes = ContextRefreshedEventListener.class)
public class ContextRefreshedEventListenerTest {
    @MockBean
    private UpdateChainHandler updateChainHandler;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Test that the ContextRefreshed event is consumed")
    @DirtiesContext
    public void testContextRefreshedEventIsConsumed() {
        Awaitility.await().atMost(ofSeconds(5)).untilAsserted(
                () -> Mockito.verify(updateChainHandler).updateChain()
        );
    }

    @Test
    @DisplayName("Test that the update chain handler is only invoked one time")
    public void testUpdateChainHandlerIsOnlyInvokedOneTime() {
        Awaitility.await().atMost(ofSeconds(5)).untilAsserted(
                () -> Mockito.verify(updateChainHandler).updateChain()
        );

        Mockito.reset(updateChainHandler);

        applicationEventPublisher.publishEvent(new ContextRefreshedEvent(
                applicationContext
        ));

        Awaitility.await().during(ofSeconds(5)).atMost(ofSeconds(6)).untilAsserted(
                () -> Mockito.verifyNoInteractions(updateChainHandler)
        );
    }
}
