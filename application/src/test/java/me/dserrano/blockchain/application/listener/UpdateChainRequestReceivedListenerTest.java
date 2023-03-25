package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import me.dserrano.blockchain.application.handler.UpdateChainRequestHandler;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static java.time.Duration.ofSeconds;
import static me.dserrano.blockchain.application.model.UpdateChainRequestMother.updateChainRequest;

@SpringBootTest(classes = UpdateChainRequestReceivedListener.class)
class UpdateChainRequestReceivedListenerTest {
    @MockBean
    private UpdateChainRequestHandler updateChainRequestHandler;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    @DisplayName("Test that UpdateChainRequest received event is consumed")
    void testUpdateChainRequestReceivedEventIsConsumed() {
        applicationEventPublisher.publishEvent(UpdateChainRequestReceived.builder()
                .updateChainRequest(updateChainRequest)
                .build()
        );
        Awaitility.await().atMost(ofSeconds(10)).untilAsserted(
                () -> Mockito.verify(updateChainRequestHandler).processUpdateChainRequest(updateChainRequest)
        );
    }
}

