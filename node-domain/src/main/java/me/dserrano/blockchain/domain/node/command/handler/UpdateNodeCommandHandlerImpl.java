package me.dserrano.blockchain.domain.node.command.handler;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeDao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateNodeCommandHandlerImpl implements UpdateNodeCommandHandler {

    private final NodeQueryService nodeQueryService;

    private final NodeDao nodeDao;

    public UpdateNodeCommandHandlerImpl(NodeQueryService nodeQueryService, NodeDao nodeDao) {
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
