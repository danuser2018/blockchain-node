package me.dserrano.blockchain.node.domain.ports.secondary;

import me.dserrano.blockchain.node.domain.model.Node;

import java.time.LocalDateTime;

public interface NodeDao {

    public void saveNodeInfo(Node node, LocalDateTime updateTime);

}
