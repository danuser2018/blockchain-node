package me.dserrano.blockchain.application.publisher;

import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UpdateChainRequestReceivedPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public UpdateChainRequestReceivedPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishChainUpdateRequestReceived(UpdateChainRequestReceived updateChainRequestReceived) {
        applicationEventPublisher.publishEvent(updateChainRequestReceived);
    }
}
