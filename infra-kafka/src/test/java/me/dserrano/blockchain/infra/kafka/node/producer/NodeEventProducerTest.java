package me.dserrano.blockchain.infra.kafka.node.producer;

import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Consumer;
import java.util.function.Function;

import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.nodeEvent;
import static me.dserrano.blockchain.infra.kafka.node.model.NodeEventMother.uuid;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = { NodeEventProducer.class, NodeEventProducerTest.TestConfig.class })
class NodeEventProducerTest {
    @TestConfiguration
    public static class TestConfig {
        @Bean
        public String nodeTopicName() {
            return "event-topic";
        }
    }

    @MockBean
    private KafkaTemplate<String, NodeEvent> kafkaTemplate;
    @MockBean
    private Consumer<SendResult<String, NodeEvent>> onNodeEventProducerSuccessAction;
    @MockBean
    private Function<Throwable, Void> onNodeEventProducerErrorAction;
    @Autowired
    private NodeEventProducer nodeEventProducer;
    @Autowired
    private String nodeTopicName;

    @Test
    @DisplayName("Produce successfully")
    void testProduceSuccessfully() {
        SendResult<String, NodeEvent> sendResult = mock(SendResult.class);

        when(kafkaTemplate.send("event-topic", uuid, nodeEvent))
                .thenReturn(CompletableFuture.completedFuture(sendResult));

        nodeEventProducer.produce(nodeEvent);

        verify(kafkaTemplate).send("event-topic", uuid, nodeEvent);
        verify(onNodeEventProducerSuccessAction).accept(sendResult);
        verifyNoInteractions(onNodeEventProducerErrorAction);
    }

    @Test
    @DisplayName("Produce error")
    void testProduceError() {
        RuntimeException exception = new RuntimeException();

        when(kafkaTemplate.send("event-topic", uuid, nodeEvent))
                .thenReturn(CompletableFuture.failedFuture(exception));

        nodeEventProducer.produce(nodeEvent);

        verify(kafkaTemplate).send("event-topic", uuid, nodeEvent);
        verify(onNodeEventProducerErrorAction).apply(any(CompletionException.class));
        verifyNoInteractions(onNodeEventProducerSuccessAction);
    }
}