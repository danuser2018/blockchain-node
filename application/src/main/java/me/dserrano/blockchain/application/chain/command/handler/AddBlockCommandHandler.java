package me.dserrano.blockchain.application.chain.command.handler;

import me.dserrano.blockchain.application.chain.command.AddBlockCommand;
import me.dserrano.blockchain.application.chain.command.NotifyBlockAddedCommand;
import me.dserrano.blockchain.application.node.command.PublishNodeCommand;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddBlockCommandHandler {

    private final ApplicationEventPublisher applicationEventPublisher;

    public AddBlockCommandHandler(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @EventListener(AddBlockCommand.class)
    public void handleAddBlockCommand(AddBlockCommand addBlockCommand) {
        applicationEventPublisher.publishEvent(NotifyBlockAddedCommand.builder().build());
    }
}
