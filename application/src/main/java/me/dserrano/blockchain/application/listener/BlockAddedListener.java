package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.event.BlockAdded;
import me.dserrano.blockchain.application.handler.UpdateChainHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BlockAddedListener {
    private final UpdateChainHandler updateChainHandler;

    public BlockAddedListener(UpdateChainHandler updateChainHandler) {
        this.updateChainHandler = updateChainHandler;
    }

    @EventListener
    public void handleBlockAddedEvent(BlockAdded blockAdded) {
        updateChainHandler.updateChain();
    }
}
