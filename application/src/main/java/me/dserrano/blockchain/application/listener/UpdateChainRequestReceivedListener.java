package me.dserrano.blockchain.application.listener;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import me.dserrano.blockchain.application.handler.UpdateChainRequestHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateChainRequestReceivedListener {
    private final UpdateChainRequestHandler updateChainRequestHandler;

    @EventListener
    public void handleChainUpdateRequestReceivedEvent(UpdateChainRequestReceived updateChainRequestReceived) {
        updateChainRequestHandler.processUpdateChainRequest(updateChainRequestReceived.updateChainRequest());
    }
}
