package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import me.dserrano.blockchain.application.handler.UpdateChainRequestHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateChainRequestReceivedListener {
    private final UpdateChainRequestHandler updateChainRequestHandler;

    public UpdateChainRequestReceivedListener(UpdateChainRequestHandler updateChainRequestHandler) {
        this.updateChainRequestHandler = updateChainRequestHandler;
    }

    @EventListener
    public void handleChainUpdateRequestReceivedEvent(UpdateChainRequestReceived updateChainRequestReceived) {
        updateChainRequestHandler.processUpdateChainRequest(updateChainRequestReceived.updateChainRequest());
    }
}
