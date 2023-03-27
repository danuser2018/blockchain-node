package me.dserrano.blockchain.application.listener;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.handler.UpdateChainHandler;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContextRefreshedEventListener {
    private final UpdateChainHandler updateChainHandler;

    private boolean eventLoopStarted = false;

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
