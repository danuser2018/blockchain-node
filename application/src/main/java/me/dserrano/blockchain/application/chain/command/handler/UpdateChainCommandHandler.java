package me.dserrano.blockchain.application.chain.command.handler;

import me.dserrano.blockchain.application.chain.command.NotifyChainUpdatedCommand;
import me.dserrano.blockchain.application.chain.command.UpdateChainCommand;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateChainCommandHandler {
    private final ApplicationEventPublisher applicationEventPublisher;

    public UpdateChainCommandHandler(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @EventListener(UpdateChainCommand.class)
    public void handleUpdateChainCommand(UpdateChainCommand updateChainCommand) {
        applicationEventPublisher.publishEvent(NotifyChainUpdatedCommand.builder().build());
    }
}
