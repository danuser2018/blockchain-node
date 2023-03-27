package me.dserrano.blockchain.application.publisher;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.event.ChainUpdated;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChainUpdatedPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishChainUpdated() {
        applicationEventPublisher.publishEvent(ChainUpdated.builder().build());
    }
}
