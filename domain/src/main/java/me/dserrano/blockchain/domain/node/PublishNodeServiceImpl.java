package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.PublishNodeService;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeEventBus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PublishNodeServiceImpl implements PublishNodeService {

    private final NodeEventBus nodeEventBus;

    public PublishNodeServiceImpl(NodeEventBus nodeEventBus) {
        this.nodeEventBus = nodeEventBus;
    }

    @Override
    public void publish(Node node, LocalDateTime dateTime) {
        nodeEventBus.publish(node, dateTime);
    }
}
