package me.dserrano.blockchain.node.domain.ports.secondary;

import me.dserrano.blockchain.node.domain.model.command.PublishNodeCommand;

public interface PublishNodeCommandHandler {
    void process(PublishNodeCommand command);
}
