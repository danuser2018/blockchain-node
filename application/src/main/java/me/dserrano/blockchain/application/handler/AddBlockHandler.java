package me.dserrano.blockchain.application.handler;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.publisher.BlockAddedPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddBlockHandler {
    private final BlockAddedPublisher blockAddedPublisher;

    public void addBlock() {
        blockAddedPublisher.publishBlockAdded();
    }
}