package me.dserrano.blockchain.infra.kafka.node.mapper;

import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = NodeEventMapperImpl.class)
class NodeEventMapperTest {

    @Autowired
    private NodeEventMapper nodeEventMapper;

    @Test
    @DisplayName("Mapping a command to a node")
    void mappingTest() {
        NodeEvent result = nodeEventMapper.toNodeEvent(node, dateTime);
        assertEquals(node.id(), result.id());
        assertEquals(node.host(), result.host());
        assertEquals(node.port(), result.port());
        assertEquals(dateTime, result.dateTime());
    }

    @Test
    @DisplayName("Mapping a NodeEvent to a UpdateNodeCommand")
    void mappingNodeEventToPublishNodeCommandTest() {
        UpdateChainRequestReceived result = nodeEventMapper.toUpdateChainRequestReceived(nodeEvent);
        assertEquals(nodeEvent.id(), result.updateChainRequest().node().id());
        assertEquals(nodeEvent.host(), result.updateChainRequest().node().host());
        assertEquals(nodeEvent.port(), result.updateChainRequest().node().port());
        assertEquals(nodeEvent.dateTime(), result.updateChainRequest().dateTime());
    }
}