package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.application.model.UpdateChainRequest;
import me.dserrano.blockchain.domain.node.ports.primary.UpdateNodeService;
import org.springframework.stereotype.Component;

@Component
public class UpdateChainRequestHandler {
    private final UpdateNodeService updateNodeService;

    public UpdateChainRequestHandler(UpdateNodeService updateNodeService) {
        this.updateNodeService = updateNodeService;
    }

    public void processUpdateChainRequest(UpdateChainRequest updateChainRequest) {
        updateNodeService.update(updateChainRequest.node(), updateChainRequest.dateTime());
    }
}
