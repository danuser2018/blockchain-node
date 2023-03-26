package me.dserrano.blockchain.domain.node;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.domain.node.model.Node;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObtainSelfNodeUseCase {
    private final Node selfNode;

    public Node getSelfNode() {
        return selfNode;
    }
}
