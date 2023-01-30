package me.dserrano.blockchain.infra.kafka.node.mapper;

import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.dateTime;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.node;

@SpringBootTest(classes = NodeEventMapperImpl.class)
class NodeEventMapperTest {

    @Autowired
    private NodeEventMapper nodeEventMapper;

    @Test
    @DisplayName("Mapping a command to a node")
    void mappingTest() {
        NodeEvent result = nodeEventMapper.toNodeEvent(node, dateTime);
        Assertions.assertEquals(node.id(), result.id());
        Assertions.assertEquals(node.host(), result.host());
        Assertions.assertEquals(node.port(), result.port());
        Assertions.assertEquals(dateTime, result.dateTime());
    }
}