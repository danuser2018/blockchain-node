package me.dserrano.blockchain.infra.h2.node.mapper;

import me.dserrano.blockchain.infra.h2.node.model.NodeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static me.dserrano.blockchain.infra.h2.node.model.NodeEntityMother.dateTime;
import static me.dserrano.blockchain.infra.h2.node.model.NodeEntityMother.node;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = NodeEntityMapperImpl.class)
public class NodeEntityMapperTest {
    @Autowired
    private NodeEntityMapper nodeEntityMapper;

    @Test
    @DisplayName("Mapping a node and a date to a node entity")
    void mappingTest() {
        NodeEntity result = nodeEntityMapper.toNodeEntity(node, dateTime);
        assertEquals(node.id(), result.getId());
        assertEquals(node.host(), result.getHost());
        assertEquals(node.port(), result.getPort());
        assertEquals(dateTime, result.getDateTime());
    }
}
