package me.dserrano.blockchain.infra.kafka.node.consumer;

import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import me.dserrano.blockchain.domain.node.ports.primary.NodeCommandService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.nodeEvent;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.updateNodeCommand;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = NodeEventConsumer.class)
class NodeEventConsumerTest {

    @MockBean
    private NodeEventMapper nodeEventMapper;

    @MockBean
    private NodeCommandService nodeCommandService;

    @Autowired
    private NodeEventConsumer nodeEventConsumer;

    @Test
    @DisplayName("When consume, then UpdateNodeCommand is send to domain")
    void consumeSuccessfully() {
        ConsumerRecord<String, NodeEvent> record = mock(ConsumerRecord.class);
        when(record.value()).thenReturn(nodeEvent);

        when(nodeEventMapper.toUpdateNodeCommand(nodeEvent)).thenReturn(updateNodeCommand);

        nodeEventConsumer.receive(record);

        verify(nodeCommandService).process(updateNodeCommand);
    }

}