package me.dserrano.blockchain.node.domain.command.handler;

import me.dserrano.blockchain.node.domain.model.Node;

import java.time.LocalDateTime;

public interface PublishNodeCommandHandler {
    void publish(Node node, LocalDateTime dateTime);
}
