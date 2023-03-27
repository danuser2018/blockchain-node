package me.dserrano.blockchain.domain.ports.secondary;

import me.dserrano.blockchain.domain.model.Node;

import java.time.LocalDateTime;

public interface NodeEventBus {
    void publish(Node node, LocalDateTime dateTime);
}
