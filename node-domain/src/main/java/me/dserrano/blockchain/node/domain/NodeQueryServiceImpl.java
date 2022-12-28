package me.dserrano.blockchain.node.domain;

import me.dserrano.blockchain.node.domain.model.Node;
import me.dserrano.blockchain.node.domain.ports.primary.NodeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class NodeQueryServiceImpl implements NodeQueryService {
    private final Node selfNode;

    @Autowired
    public NodeQueryServiceImpl(Node selfNode) {
        this.selfNode = selfNode;
    }

    @Override
    public Node getSelfNode() {
        return selfNode;
    }
}
