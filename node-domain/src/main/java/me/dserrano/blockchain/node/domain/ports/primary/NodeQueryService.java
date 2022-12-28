package me.dserrano.blockchain.node.domain.ports.primary;

import me.dserrano.blockchain.node.domain.model.Node;

public interface NodeQueryService {
    Node getSelfNode();
}
