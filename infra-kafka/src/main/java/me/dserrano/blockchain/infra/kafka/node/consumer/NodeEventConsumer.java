package me.dserrano.blockchain.infra.kafka.node.consumer;

import lombok.extern.slf4j.Slf4j;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NodeEventConsumer {
    @KafkaListener(topics = "${blockchain.nodes.topic.name}")
    public void receive(ConsumerRecord<String, NodeEvent> record) {
        log.info("Status notification received for node [" + record.value().id() + "]");
    }
}
