package me.dserrano.blockchain.node.domain;

import lombok.extern.slf4j.Slf4j;
import me.dserrano.blockchain.node.domain.command.handler.PublishNodeCommandHandler;
import me.dserrano.blockchain.node.domain.model.command.PublishNodeCommand;
import me.dserrano.blockchain.node.domain.model.command.UpdateNodeCommand;
import me.dserrano.blockchain.node.domain.ports.primary.NodeCommandService;
import me.dserrano.blockchain.node.domain.ports.secondary.UpdateNodeCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
            updateNodeCommandHandler.process(updateNodeCommand);
        } else {
            throw new IllegalArgumentException("Unknown command type [" + command.getClass().getName() + "]");
        }
    }
}
