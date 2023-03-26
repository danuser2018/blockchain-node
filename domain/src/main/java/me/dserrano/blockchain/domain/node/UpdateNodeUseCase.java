package me.dserrano.blockchain.domain.node;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import me.dserrano.blockchain.domain.node.ports.primary.UpdateNodeService;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeDao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateNodeUseCase implements UpdateNodeService {

    private final NodeQueryService nodeQueryService;

    private final NodeDao nodeDao;

    public UpdateNodeUseCase(NodeQueryService nodeQueryService, NodeDao nodeDao) {
        this.nodeQueryService = nodeQueryService;
        this.nodeDao = nodeDao;
    }

    @Override
    public void update(Node node, LocalDateTime dateTime) {
        if (isNotSelfNode(node)) {
            nodeDao.saveNodeInfo(node, dateTime);
        }
    }

    private boolean isNotSelfNode(Node node) {
        Node selfNode = nodeQueryService.getSelfNode();
        return !selfNode.id().equals(node.id());
    }
}
