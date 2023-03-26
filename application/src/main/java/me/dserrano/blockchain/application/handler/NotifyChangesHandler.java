package me.dserrano.blockchain.application.handler;

import lombok.RequiredArgsConstructor;
import me.dserrano.blockchain.domain.node.ports.primary.NodeService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class NotifyChangesHandler {
    private final NodeService nodeService;
    private final Supplier<LocalDateTime> nowSupplier;

    public void notifyChanges() {
        nodeService.publishSelfNode(nowSupplier.get());
    }
}
