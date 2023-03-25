package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.handler.UpdateChainHandler;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedEventListener {
    private final UpdateChainHandler updateChainHandler;

    private boolean eventLoopStarted = false;

    public ContextRefreshedEventListener(UpdateChainHandler updateChainHandler) {
        this.updateChainHandler = updateChainHandler;
    }

    @EventListener
    public void handleContextStartedEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!eventLoopStarted) {
            startEventLoop();
        }
    }

    private void startEventLoop() {
        updateChainHandler.updateChain();
        eventLoopStarted = true;
    }
}
