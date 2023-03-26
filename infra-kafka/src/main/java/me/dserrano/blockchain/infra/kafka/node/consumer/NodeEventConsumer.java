package me.dserrano.blockchain.infra.kafka.node.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dserrano.blockchain.application.publisher.UpdateChainRequestReceivedPublisher;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NodeEventConsumer {
    private final NodeEventMapper nodeEventMapper;
    private final UpdateChainRequestReceivedPublisher updateChainRequestReceivedPublisher;

    @KafkaListener(topics = "${blockchain.nodes.topic.name}")
    public void receive(ConsumerRecord<String, NodeEvent> event) {
        log.info("Status notification received for node [" + event.value().id() + "]");
        updateChainRequestReceivedPublisher.publishChainUpdateRequestReceived(
                nodeEventMapper.toUpdateChainRequestReceived(event.value())
        );
    }
}
