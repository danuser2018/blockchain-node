package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.application.publisher.BlockAddedPublisher;
import org.springframework.stereotype.Component;

@Component
public class AddBlockHandler {
    private final BlockAddedPublisher blockAddedPublisher;

    public AddBlockHandler(BlockAddedPublisher blockAddedPublisher) {
        this.blockAddedPublisher = blockAddedPublisher;
    }

    public void addBlock() {
        blockAddedPublisher.publishBlockAdded();
    }
}