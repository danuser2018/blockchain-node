package me.dserrano.blockchain.infra.h2.node;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeDao;
import me.dserrano.blockchain.infra.h2.node.mapper.NodeEntityMapper;
import me.dserrano.blockchain.infra.h2.node.repository.NodeEntityRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
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
