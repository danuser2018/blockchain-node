package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.PublishNodeService;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeEventBus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PublishNodeUseCase implements PublishNodeService {

    private final NodeEventBus nodeEventBus;

    public PublishNodeUseCase(NodeEventBus nodeEventBus) {
        this.nodeEventBus = nodeEventBus;
    }

    @Override
    public void publish(Node node, LocalDateTime dateTime) {
        nodeEventBus.publish(node, dateTime);
    }
}
