package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.event.ChainUpdated;
import me.dserrano.blockchain.application.handler.AddBlockHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChainUpdatedListener {
    private final AddBlockHandler addBlockHandler;

    public ChainUpdatedListener(AddBlockHandler addBlockHandler) {
        this.addBlockHandler = addBlockHandler;
    }

    @EventListener
    public void handleChainUpdatedEvent(ChainUpdated chainUpdated) {
        addBlockHandler.addBlock();
    }
}
