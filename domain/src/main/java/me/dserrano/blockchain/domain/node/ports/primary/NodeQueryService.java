package me.dserrano.blockchain.domain.node.ports.primary;

import me.dserrano.blockchain.domain.node.model.Node;

public interface NodeQueryService {
    Node getSelfNode();
}
