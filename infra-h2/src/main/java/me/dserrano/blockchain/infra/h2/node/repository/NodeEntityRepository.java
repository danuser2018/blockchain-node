package me.dserrano.blockchain.infra.h2.node.repository;

import me.dserrano.blockchain.infra.h2.node.model.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeEntityRepository extends JpaRepository<NodeEntity, String> {
}
