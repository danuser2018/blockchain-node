package me.dserrano.blockchain.application.handler;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.publisher.ChainUpdatedPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateChainHandler {
    private final ChainUpdatedPublisher chainUpdatedPublisher;

    public void updateChain() {
        chainUpdatedPublisher.publishChainUpdated();
    }
}