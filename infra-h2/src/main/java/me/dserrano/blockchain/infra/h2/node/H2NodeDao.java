package me.dserrano.blockchain.infra.h2.node;

import me.dserrano.blockchain.infra.h2.node.mapper.NodeEntityMapper;
import me.dserrano.blockchain.infra.h2.node.repository.NodeEntityRepository;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeDao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class H2NodeDao implements NodeDao {
    private final NodeEntityMapper nodeEntityMapper;
    private final NodeEntityRepository nodeEntityRepository;

    public H2NodeDao(NodeEntityMapper nodeEntityMapper, NodeEntityRepository nodeEntityRepository) {
        this.nodeEntityMapper = nodeEntityMapper;
        this.nodeEntityRepository = nodeEntityRepository;
    }

    @Override
    public void saveNodeInfo(Node node, LocalDateTime updateTime) {
        nodeEntityRepository.save(nodeEntityMapper.toNodeEntity(node, updateTime));
    }
}
