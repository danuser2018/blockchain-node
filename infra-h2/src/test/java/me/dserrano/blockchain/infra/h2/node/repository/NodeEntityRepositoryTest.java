package me.dserrano.blockchain.infra.h2.node.repository;

import me.dserrano.blockchain.infra.h2.node.model.NodeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static me.dserrano.blockchain.infra.h2.node.model.NodeEntityMother.nodeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class NodeEntityRepositoryTest {
    @Autowired
    private NodeEntityRepository nodeEntityRepository;

    @Test
    @DisplayName("Save a node entity")
    void testSave() {
        NodeEntity result = nodeEntityRepository.save(nodeEntity);

        assertEquals(nodeEntity, result);
    }
}
