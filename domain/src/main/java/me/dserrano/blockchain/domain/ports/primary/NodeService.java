package me.dserrano.blockchain.domain.ports.primary;

import me.dserrano.blockchain.domain.model.Node;

import java.time.LocalDateTime;

public interface NodeService {
    Node getSelfNode();

    void publishSelfNode(LocalDateTime dateTime);

    void storeOtherNode(Node node, LocalDateTime dateTime);
}
