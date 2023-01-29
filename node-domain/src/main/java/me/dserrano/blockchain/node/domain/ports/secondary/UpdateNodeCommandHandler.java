package me.dserrano.blockchain.node.domain.ports.secondary;

import me.dserrano.blockchain.node.domain.model.command.UpdateNodeCommand;

public interface UpdateNodeCommandHandler {

    void process(UpdateNodeCommand command);
}
