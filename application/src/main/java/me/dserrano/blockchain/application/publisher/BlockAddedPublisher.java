package me.dserrano.blockchain.application.publisher;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.event.BlockAdded;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockAddedPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishBlockAdded() {
        applicationEventPublisher.publishEvent(BlockAdded.builder().build());
    }
}
