package me.dserrano.blockchain.infra.h2.node;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.domain.model.Node;
import me.dserrano.blockchain.domain.ports.secondary.NodeDao;
import me.dserrano.blockchain.infra.h2.node.mapper.NodeEntityMapper;
import me.dserrano.blockchain.infra.h2.node.repository.NodeEntityRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class H2NodeDao implements NodeDao {
    private final NodeEntityMapper nodeEntityMapper;
    private final NodeEntityRepository nodeEntityRepository;

    @Override
    public void saveNodeInfo(Node node, LocalDateTime updateTime) {
        nodeEntityRepository.save(nodeEntityMapper.toNodeEntity(node, updateTime));
    }
}
