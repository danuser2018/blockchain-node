package me.dserrano.blockchain.infra.h2.node;

import me.dserrano.blockchain.infra.h2.node.mapper.NodeEntityMapper;
import me.dserrano.blockchain.infra.h2.node.repository.NodeEntityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static me.dserrano.blockchain.infra.h2.node.model.NodeEntityMother.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = H2NodeDao.class)
public class H2NodeDaoTest {
    @MockBean
    private NodeEntityMapper nodeEntityMapper;

    @MockBean
    private NodeEntityRepository nodeEntityRepository;

    @Autowired
    private H2NodeDao h2NodeDao;

    @Test
    @DisplayName("Given a node and a date, they are sent to the repository")
    void testSave() {
        when(nodeEntityMapper.toNodeEntity(node, dateTime)).thenReturn(nodeEntity);

        h2NodeDao.saveNodeInfo(node, dateTime);

        verify(nodeEntityRepository).save(nodeEntity);
    }
}
