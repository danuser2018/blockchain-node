package me.dserrano.blockchain.domain.node;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeDao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateNodeUseCase {
    private final NodeDao nodeDao;

    public void update(Node node, LocalDateTime dateTime) {
        nodeDao.saveNodeInfo(node, dateTime);
    }
}
