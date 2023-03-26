package me.dserrano.blockchain.domain.node.ports.primary;

import me.dserrano.blockchain.domain.node.model.Node;

import java.time.LocalDateTime;

public interface NodeService {
    Node getSelfNode();

    void publishSelfNode(LocalDateTime dateTime);

    void storeOtherNode(Node node, LocalDateTime dateTime);
}
