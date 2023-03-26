package me.dserrano.blockchain.domain.node.service;

import me.dserrano.blockchain.domain.node.GetSelfNodeUseCase;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import org.springframework.stereotype.Service;

@Service
public class NodeQueryServiceImpl implements NodeQueryService {
    private final GetSelfNodeUseCase getSelfNodeUseCase;

    public NodeQueryServiceImpl(GetSelfNodeUseCase getSelfNodeUseCase) {
        this.getSelfNodeUseCase = getSelfNodeUseCase;
    }

    @Override
    public Node getSelfNode() {
        return getSelfNodeUseCase.getSelfNode();
    }
}
