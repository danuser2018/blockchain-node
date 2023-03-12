package me.dserrano.blockchain.application.node.command.handler;

import lombok.extern.slf4j.Slf4j;
import me.dserrano.blockchain.application.node.command.PublishNodeCommand;
import me.dserrano.blockchain.domain.node.ports.primary.PublishNodeService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PublishNodeCommandHandler {
    private final PublishNodeService publishNodeService;

    public PublishNodeCommandHandler(PublishNodeService publishNodeService) {
        this.publishNodeService = publishNodeService;
    }

    @EventListener(PublishNodeCommand.class)
    public void handlePublishNodeCommand(PublishNodeCommand publishNodeCommand) {
        publishNodeService.publish(publishNodeCommand.node(), publishNodeCommand.dateTime());
    }
}
