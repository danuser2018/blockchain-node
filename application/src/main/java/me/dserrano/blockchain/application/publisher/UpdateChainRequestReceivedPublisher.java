package me.dserrano.blockchain.application.publisher;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateChainRequestReceivedPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishChainUpdateRequestReceived(UpdateChainRequestReceived updateChainRequestReceived) {
        applicationEventPublisher.publishEvent(updateChainRequestReceived);
    }
}
