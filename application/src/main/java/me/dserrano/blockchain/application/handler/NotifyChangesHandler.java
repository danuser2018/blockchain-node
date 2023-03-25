package me.dserrano.blockchain.application.handler;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import me.dserrano.blockchain.domain.node.ports.primary.PublishNodeService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Component
public class NotifyChangesHandler {
    private final NodeQueryService nodeQueryService;
    private final PublishNodeService publishNodeService;
    private final Supplier<LocalDateTime> nowSupplier;

    public NotifyChangesHandler(
            NodeQueryService nodeQueryService,
            PublishNodeService publishNodeService,
            Supplier<LocalDateTime> nowSupplier
    ) {
        this.nodeQueryService = nodeQueryService;
        this.publishNodeService = publishNodeService;
        this.nowSupplier = nowSupplier;
    }

    public void notifyChanges() {
        Node selfNode = nodeQueryService.getSelfNode();
        publishNodeService.publish(selfNode, nowSupplier.get());
    }
}
