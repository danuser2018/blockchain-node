package me.dserrano.blockchain.application.publisher;

import me.dserrano.blockchain.application.event.BlockAdded;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class BlockAddedPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public BlockAddedPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishBlockAdded() {
        applicationEventPublisher.publishEvent(BlockAdded.builder().build());
    }
}
