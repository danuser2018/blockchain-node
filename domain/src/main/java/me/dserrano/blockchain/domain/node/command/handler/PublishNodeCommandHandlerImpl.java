package me.dserrano.blockchain.domain.node.command.handler;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeEventBus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PublishNodeCommandHandlerImpl implements PublishNodeCommandHandler {

    private final NodeEventBus nodeEventBus;

    public PublishNodeCommandHandlerImpl(NodeEventBus nodeEventBus) {
        this.nodeEventBus = nodeEventBus;
    }

    @Override
    public void publish(Node node, LocalDateTime dateTime) {
        nodeEventBus.publish(node, dateTime);
    }
}
