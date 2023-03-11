package me.dserrano.blockchain.infra.kafka.node;

import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.producer.NodeEventProducer;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.secondary.NodeEventBus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KafkaNodeEventBus implements NodeEventBus {
    private final NodeEventMapper nodeEventMapper;
    private final NodeEventProducer nodeEventProducer;

    public KafkaNodeEventBus(NodeEventMapper nodeEventMapper, NodeEventProducer nodeEventProducer) {
        this.nodeEventMapper = nodeEventMapper;
        this.nodeEventProducer = nodeEventProducer;
    }

    @Override
    public void publish(Node node, LocalDateTime dateTime) {
        nodeEventProducer.produce(nodeEventMapper.toNodeEvent(node, dateTime));
    }
}
