package me.dserrano.blockchain.infra.kafka.node;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.domain.model.Node;
import me.dserrano.blockchain.domain.ports.secondary.NodeEventBus;
import me.dserrano.blockchain.infra.kafka.node.mapper.NodeEventMapper;
import me.dserrano.blockchain.infra.kafka.node.producer.NodeEventProducer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class KafkaNodeEventBus implements NodeEventBus {
    private final NodeEventMapper nodeEventMapper;
    private final NodeEventProducer nodeEventProducer;

    @Override
    public void publish(Node node, LocalDateTime dateTime) {
        nodeEventProducer.produce(nodeEventMapper.toNodeEvent(node, dateTime));
    }
}
