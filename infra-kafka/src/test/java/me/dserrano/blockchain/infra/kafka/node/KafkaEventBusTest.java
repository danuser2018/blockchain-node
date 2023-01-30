package me.dserrano.blockchain.infra.kafka.node;

import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.producer.NodeEventProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = KafkaEventBus.class)
class KafkaEventBusTest {
    @MockBean
    private NodeEventMapper nodeEventMapper;

    @MockBean
    private NodeEventProducer nodeEventProducer;

    @Autowired
    private KafkaEventBus kafkaEventBus;

    @Test
    @DisplayName("Given a Node and a DateTime then it produces in kafka node event topic")
    void testNodeEventProduction() {
        when(nodeEventMapper.toNodeEvent(node, dateTime)).thenReturn(nodeEvent);

        kafkaEventBus.publish(node, dateTime);

        verify(nodeEventMapper).toNodeEvent(node, dateTime);
        verify(nodeEventProducer).produce(nodeEvent);
    }
}