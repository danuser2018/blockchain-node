package me.dserrano.blockchain.infra.kafka.node.producer;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class NodeEventProducer {
    private final String nodeTopicName;
    private final KafkaTemplate<String, NodeEvent> kafkaTemplate;
    private final Consumer<SendResult<String, NodeEvent>> onNodeEventProducerSuccessAction;
    private final Function<Throwable, Void> onNodeEventProducerErrorAction;

    public void produce(NodeEvent nodeEvent) {
        kafkaTemplate.send(nodeTopicName, nodeEvent.id(), nodeEvent)
                .thenAccept(onNodeEventProducerSuccessAction)
                .exceptionally(onNodeEventProducerErrorAction);
    }
}
