package me.dserrano.blockchain.node.domain.command.handler;

import me.dserrano.blockchain.node.domain.model.Node;

import java.time.LocalDateTime;

public interface UpdateNodeCommandHandler {

    void update(Node node, LocalDateTime dateTime);
}
