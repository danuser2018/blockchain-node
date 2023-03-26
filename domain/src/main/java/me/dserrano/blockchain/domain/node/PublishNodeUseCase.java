package me.dserrano.blockchain.domain.node;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeEventBus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PublishNodeUseCase {
    private final NodeEventBus nodeEventBus;

    public void publish(Node node, LocalDateTime dateTime) {
        nodeEventBus.publish(node, dateTime);
    }
}
