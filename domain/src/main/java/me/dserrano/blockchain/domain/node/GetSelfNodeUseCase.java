package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.model.Node;
import org.springframework.stereotype.Component;

@Component
public class GetSelfNodeUseCase {
    private final Node selfNode;

    public GetSelfNodeUseCase(Node selfNode) {
        this.selfNode = selfNode;
    }

    public Node getSelfNode() {
        return selfNode;
    }
}
