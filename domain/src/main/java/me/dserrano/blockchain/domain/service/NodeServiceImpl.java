package me.dserrano.blockchain.domain.service;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.domain.usecase.node.ObtainSelfNodeUseCase;
import me.dserrano.blockchain.domain.usecase.node.PublishNodeUseCase;
import me.dserrano.blockchain.domain.usecase.node.UpdateNodeUseCase;
import me.dserrano.blockchain.domain.model.Node;
import me.dserrano.blockchain.domain.ports.primary.NodeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NodeServiceImpl implements NodeService {
    private final ObtainSelfNodeUseCase obtainSelfNodeUseCase;
    private final PublishNodeUseCase publishNodeUseCase;
    private final UpdateNodeUseCase updateNodeUseCase;

    @Override
    public Node getSelfNode() {
        return obtainSelfNodeUseCase.getSelfNode();
    }

    @Override
    public void publishSelfNode(LocalDateTime dateTime) {
        Node selfNode = obtainSelfNodeUseCase.getSelfNode();
        publishNodeUseCase.publish(selfNode, dateTime);
    }

    @Override
    public void storeOtherNode(Node node, LocalDateTime dateTime) {
        if (isNotSelfNode(node)) {
            updateNodeUseCase.update(node, dateTime);
        }
    }

    private boolean isNotSelfNode(Node node) {
        Node selfNode = obtainSelfNodeUseCase.getSelfNode();
        return !selfNode.id().equals(node.id());
    }
}
