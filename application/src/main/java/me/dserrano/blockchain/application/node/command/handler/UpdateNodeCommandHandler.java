package me.dserrano.blockchain.application.node.command.handler;

import me.dserrano.blockchain.application.node.command.UpdateNodeCommand;
import me.dserrano.blockchain.domain.node.ports.primary.UpdateNodeService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateNodeCommandHandler {
    private final UpdateNodeService updateNodeService;

    public UpdateNodeCommandHandler(UpdateNodeService updateNodeService) {
        this.updateNodeService = updateNodeService;
    }

    @EventListener(UpdateNodeCommand.class)
    public void handleUpdateNodeCommand(UpdateNodeCommand updateNodeCommand) {
        updateNodeService.update(updateNodeCommand.node(), updateNodeCommand.dateTime());
    }
}
