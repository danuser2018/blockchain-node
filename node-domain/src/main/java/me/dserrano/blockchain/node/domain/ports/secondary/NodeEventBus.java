package me.dserrano.blockchain.node.domain.ports.secondary;

import me.dserrano.blockchain.node.domain.model.Node;

import java.time.LocalDateTime;

public interface NodeEventBus {
    void publish(Node node, LocalDateTime dateTime);
}
