package me.dserrano.blockchain.domain.node.command.handler;

import me.dserrano.blockchain.domain.node.model.Node;

import java.time.LocalDateTime;

public interface PublishNodeCommandHandler {
    void publish(Node node, LocalDateTime dateTime);
}
