package me.dserrano.blockchain.infra.kafka.node;

import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.producer.NodeEventProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.nodeEvent;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.publishNodeCommand;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = KafkaPublishNodeCommandHandler.class)
class KafkaPublishNodeCommandHandlerTest {
    @MockBean
    private NodeEventMapper nodeEventMapper;

    @MockBean
    private NodeEventProducer nodeEventProducer;

    @Autowired
    private KafkaPublishNodeCommandHandler kafkaPublishNodeCommandHandler;

    @Test
    @DisplayName("Given a PublishNodeCommand then it produces in kafka node event topic")
    void testNodeEventProduction() {
        when(nodeEventMapper.toNodeEvent(publishNodeCommand)).thenReturn(nodeEvent);

        kafkaPublishNodeCommandHandler.process(publishNodeCommand);

        verify(nodeEventMapper).toNodeEvent(publishNodeCommand);
        verify(nodeEventProducer).produce(nodeEvent);
    }
}