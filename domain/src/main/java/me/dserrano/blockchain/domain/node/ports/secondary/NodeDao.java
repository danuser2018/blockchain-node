package me.dserrano.blockchain.domain.node.ports.secondary;

import me.dserrano.blockchain.domain.node.model.Node;

import java.time.LocalDateTime;

public interface NodeDao {

    public void saveNodeInfo(Node node, LocalDateTime updateTime);

}
