package me.dserrano.blockchain.infra.kafka.node.consumer;

import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import me.dserrano.blockchain.application.publisher.UpdateChainRequestReceivedPublisher;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.time.Duration.ofSeconds;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {NodeEventConsumer.class})
class NodeEventConsumerTest {
    @MockBean
    private NodeEventMapper nodeEventMapper;

    @MockBean
    private UpdateChainRequestReceivedPublisher updateChainRequestReceivedPublisher;

    @Autowired
    private NodeEventConsumer nodeEventConsumer;

    @Test
    @DisplayName("When consume, then UpdateChainRequestReceived is published")
    void consumeSuccessfully() {
        ConsumerRecord record = mock(ConsumerRecord.class);
        when(record.value()).thenReturn(nodeEvent);

        var event = new UpdateChainRequestReceived(updateChainRequest);

        when(nodeEventMapper.toUpdateChainRequestReceived(nodeEvent)).thenReturn(event);

        nodeEventConsumer.receive(record);

        Awaitility.await()
                .atMost(ofSeconds(10))
                .untilAsserted(() -> Mockito.verify(updateChainRequestReceivedPublisher)
                        .publishChainUpdateRequestReceived(event)
                );
    }
}