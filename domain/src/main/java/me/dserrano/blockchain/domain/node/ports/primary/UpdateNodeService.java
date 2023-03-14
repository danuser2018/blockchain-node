package me.dserrano.blockchain.domain.node.ports.primary;

import me.dserrano.blockchain.domain.node.model.Node;

import java.time.LocalDateTime;

public interface UpdateNodeService {

    void update(Node node, LocalDateTime dateTime);
}
