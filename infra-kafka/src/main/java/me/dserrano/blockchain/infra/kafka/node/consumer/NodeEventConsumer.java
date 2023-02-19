package me.dserrano.blockchain.infra.kafka.node.consumer;

import lombok.extern.slf4j.Slf4j;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import me.dserrano.blockchain.node.domain.ports.primary.NodeCommandService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NodeEventConsumer {
    private final NodeEventMapper nodeEventMapper;
    private final NodeCommandService nodeCommandService;

    public NodeEventConsumer(NodeEventMapper nodeEventMapper, NodeCommandService nodeCommandService) {
        this.nodeEventMapper = nodeEventMapper;
        this.nodeCommandService = nodeCommandService;
    }

    @KafkaListener(topics = "${blockchain.nodes.topic.name}")
    public void receive(ConsumerRecord<String, NodeEvent> event) {
        log.info("Status notification received for node [" + event.value().id() + "]");
        nodeCommandService.process(nodeEventMapper.toUpdateNodeCommand(event.value()));
    }
}
