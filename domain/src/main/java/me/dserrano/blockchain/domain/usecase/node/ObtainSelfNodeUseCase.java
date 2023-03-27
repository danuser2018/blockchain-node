package me.dserrano.blockchain.domain.usecase.node;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.domain.model.Node;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObtainSelfNodeUseCase {
    private final Node selfNode;

    public Node getSelfNode() {
        return selfNode;
    }
}
