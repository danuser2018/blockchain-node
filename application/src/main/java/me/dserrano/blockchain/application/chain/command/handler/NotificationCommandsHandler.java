package me.dserrano.blockchain.application.chain.command.handler;

import me.dserrano.blockchain.application.chain.command.AddBlockCommand;
import me.dserrano.blockchain.application.chain.command.NotifyBlockAddedCommand;
import me.dserrano.blockchain.application.chain.command.NotifyChainUpdatedCommand;
import me.dserrano.blockchain.application.chain.command.UpdateChainCommand;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationCommandsHandler {
    private final ApplicationEventPublisher applicationEventPublisher;

    public NotificationCommandsHandler(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @EventListener(NotifyBlockAddedCommand.class)
    public void handleNotifyBlockAddedCommand(NotifyBlockAddedCommand notifyBlockAddedCommand) {
        applicationEventPublisher.publishEvent(UpdateChainCommand.builder().build());
    }

    @EventListener(NotifyChainUpdatedCommand.class)
    public void handleNotifyChainUpdatedCommand(NotifyChainUpdatedCommand notifyChainUpdatedCommand) {
        applicationEventPublisher.publishEvent(AddBlockCommand.builder().build());
    }
}
