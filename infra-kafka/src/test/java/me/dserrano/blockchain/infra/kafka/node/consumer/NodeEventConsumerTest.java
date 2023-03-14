package me.dserrano.blockchain.infra.kafka.node.consumer;

import lombok.Data;
import me.dserrano.blockchain.application.node.command.PublishNodeCommand;
import me.dserrano.blockchain.application.node.command.UpdateNodeCommand;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static java.time.Duration.ofMinutes;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = { NodeEventConsumer.class, NodeEventConsumerTest.TestListener.class })
class NodeEventConsumerTest {
    @Data
    @Component
    public static class TestListener {
        private boolean consumed = false;
        private UpdateNodeCommand payload;

        @EventListener(UpdateNodeCommand.class)
        public void consumeEvent(UpdateNodeCommand command) {
            payload = command;
            consumed = true;
        }
    }

    @Autowired
    TestListener testListener;

    @MockBean
    private NodeEventMapper nodeEventMapper;

    @Autowired
    private NodeEventConsumer nodeEventConsumer;

    @Test
    @DisplayName("When consume, then UpdateNodeCommand is send to domain")
    void consumeSuccessfully() {
        ConsumerRecord record = mock(ConsumerRecord.class);
        when(record.value()).thenReturn(nodeEvent);

        when(nodeEventMapper.toUpdateNodeCommand(nodeEvent)).thenReturn(updateNodeCommand);

        nodeEventConsumer.receive(record);

        Awaitility.await()
                .atMost(ofMinutes(1))
                .untilAsserted(() -> Assertions.assertTrue(testListener.isConsumed()));

        var result = testListener.getPayload();

        Assertions.assertEquals(uuid, result.node().id());
        Assertions.assertEquals(host, result.node().host());
        Assertions.assertEquals(port, result.node().port());
        Assertions.assertEquals(dateTime, result.dateTime());
    }

}