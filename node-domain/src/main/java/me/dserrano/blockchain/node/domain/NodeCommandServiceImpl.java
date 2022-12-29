package me.dserrano.blockchain.node.domain;

import lombok.extern.slf4j.Slf4j;
import me.dserrano.blockchain.node.domain.model.command.PublishNodeCommand;
import me.dserrano.blockchain.node.domain.ports.primary.NodeCommandService;
import me.dserrano.blockchain.node.domain.ports.secondary.PublishNodeCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class NodeCommandServiceImpl implements NodeCommandService {
    private final PublishNodeCommandHandler publishNodeCommandHandler;

    @Autowired
    public NodeCommandServiceImpl(
            PublishNodeCommandHandler publishNodeCommandHandler
    ) {
        this.publishNodeCommandHandler = publishNodeCommandHandler;
    }

    @Override
    public <T> void process(T command) {
        if (command instanceof PublishNodeCommand publishNodeCommand) {
            publishNodeCommandHandler.process(publishNodeCommand);
        } else {
            throw new IllegalArgumentException("Unknown command type [" + command.getClass().getName() + "]");
        }
    }
}
