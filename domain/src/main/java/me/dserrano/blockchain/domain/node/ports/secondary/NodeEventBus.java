package me.dserrano.blockchain.domain.node.ports.secondary;

import me.dserrano.blockchain.domain.node.model.Node;

import java.time.LocalDateTime;

public interface NodeEventBus {
    void publish(Node node, LocalDateTime dateTime);
}
