package me.dserrano.blockchain.infra.kafka.node.consumer;

import lombok.extern.slf4j.Slf4j;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NodeEventConsumer {
    private final NodeEventMapper nodeEventMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public NodeEventConsumer(NodeEventMapper nodeEventMapper, ApplicationEventPublisher applicationEventPublisher) {
        this.nodeEventMapper = nodeEventMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @KafkaListener(topics = "${blockchain.nodes.topic.name}")
    public void receive(ConsumerRecord<String, NodeEvent> event) {
        log.info("Status notification received for node [" + event.value().id() + "]");
        applicationEventPublisher.publishEvent(nodeEventMapper.toUpdateNodeCommand(event.value()));
    }
}
