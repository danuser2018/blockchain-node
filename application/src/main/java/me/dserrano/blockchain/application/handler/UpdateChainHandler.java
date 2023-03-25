package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.application.publisher.ChainUpdatedPublisher;
import org.springframework.stereotype.Component;

@Component
public class UpdateChainHandler {
    private final ChainUpdatedPublisher chainUpdatedPublisher;

    public UpdateChainHandler(ChainUpdatedPublisher chainUpdatedPublisher) {
        this.chainUpdatedPublisher = chainUpdatedPublisher;
    }

    public void updateChain() {
        chainUpdatedPublisher.publishChainUpdated();
    }
}