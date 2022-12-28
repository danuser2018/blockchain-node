package me.dserrano.blockchain.node.infra.kafka.producer;

import me.dserrano.blockchain.node.domain.model.command.PublishNodeCommand;
import me.dserrano.blockchain.node.domain.ports.secondary.PublishNodeCommandHandler;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublishNodeCommandHandler implements PublishNodeCommandHandler {
    @Override
    public void process(PublishNodeCommand command) {
        System.out.println(command.node() + " " + command.dateTime());
    }
}
