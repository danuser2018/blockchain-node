package me.dserrano.blockchain.application.listener;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.event.ChainUpdated;
import me.dserrano.blockchain.application.handler.AddBlockHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChainUpdatedListener {
    private final AddBlockHandler addBlockHandler;

    @EventListener
    public void handleChainUpdatedEvent(ChainUpdated chainUpdated) {
        addBlockHandler.addBlock();
    }
}
