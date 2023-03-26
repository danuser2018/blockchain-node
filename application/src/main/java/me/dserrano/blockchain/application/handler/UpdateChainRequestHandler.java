package me.dserrano.blockchain.application.handler;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.application.model.UpdateChainRequest;
import me.dserrano.blockchain.domain.node.ports.primary.NodeService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateChainRequestHandler {
    private final NodeService nodeService;

    public void processUpdateChainRequest(UpdateChainRequest updateChainRequest) {
        nodeService.storeOtherNode(updateChainRequest.node(), updateChainRequest.dateTime());
    }
}
