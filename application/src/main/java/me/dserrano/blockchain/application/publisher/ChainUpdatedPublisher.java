package me.dserrano.blockchain.application.publisher;

import me.dserrano.blockchain.application.event.ChainUpdated;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ChainUpdatedPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public ChainUpdatedPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishChainUpdated() {
        applicationEventPublisher.publishEvent(ChainUpdated.builder().build());
    }
}
