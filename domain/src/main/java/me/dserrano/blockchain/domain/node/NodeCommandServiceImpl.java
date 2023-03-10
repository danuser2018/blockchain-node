package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.command.PublishNodeCommand;
import me.dserrano.blockchain.domain.node.command.UpdateNodeCommand;
import me.dserrano.blockchain.domain.node.command.handler.PublishNodeCommandHandler;
import me.dserrano.blockchain.domain.node.ports.primary.NodeCommandService;
import me.dserrano.blockchain.domain.node.command.handler.UpdateNodeCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class NodeCommandServiceImpl implements NodeCommandService {
    private final PublishNodeCommandHandler publishNodeCommandHandler;
    private final UpdateNodeCommandHandler updateNodeCommandHandler;

    @Autowired
    public NodeCommandServiceImpl(
            PublishNodeCommandHandler publishNodeCommandHandler,
            UpdateNodeCommandHandler updateNodeCommandHandler
    ) {
        this.publishNodeCommandHandler = publishNodeCommandHandler;
        this.updateNodeCommandHandler = updateNodeCommandHandler;
    }

    @Override
    public <T> void process(T command) {
        if (command instanceof PublishNodeCommand publishNodeCommand) {
            publishNodeCommandHandler.publish(publishNodeCommand.node(), publishNodeCommand.dateTime());
        } else if (command instanceof UpdateNodeCommand updateNodeCommand) {
            updateNodeCommandHandler.update(updateNodeCommand.node(), updateNodeCommand.dateTime());
        } else {
            throw new IllegalArgumentException("Unknown command type [" + command.getClass().getName() + "]");
        }
    }
}
