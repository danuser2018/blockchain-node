package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.command.UpdateNodeCommand;
import me.dserrano.blockchain.domain.node.command.handler.UpdateNodeCommandHandler;
import me.dserrano.blockchain.domain.node.ports.primary.NodeCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class NodeCommandServiceImpl implements NodeCommandService {
    private final UpdateNodeCommandHandler updateNodeCommandHandler;

    @Autowired
    public NodeCommandServiceImpl(UpdateNodeCommandHandler updateNodeCommandHandler) {
        this.updateNodeCommandHandler = updateNodeCommandHandler;
    }

    @Override
    public <T> void process(T command) {
        if (command instanceof UpdateNodeCommand updateNodeCommand) {
            updateNodeCommandHandler.update(updateNodeCommand.node(), updateNodeCommand.dateTime());
        } else {
            throw new IllegalArgumentException("Unknown command type [" + command.getClass().getName() + "]");
        }
    }
}
