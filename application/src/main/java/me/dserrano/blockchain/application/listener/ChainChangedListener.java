package me.dserrano.blockchain.application.listener;

import me.dserrano.blockchain.application.event.ChainChanged;
import me.dserrano.blockchain.application.handler.NotifyChangesHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class ChainChangedListener {
    private final NotifyChangesHandler notifyChangesHandler;
    private final Supplier<Long> currentTimeMillisSupplier;
    @Value("${blockchain.nodes.self.publish-rate-ms}")
    private long publishRateMs;
    private long lastTimePublished = 0L;

    public ChainChangedListener(
            NotifyChangesHandler notifyChangesHandler,
            Supplier<Long> currentTimeMillisSupplier
    ) {
        this.notifyChangesHandler = notifyChangesHandler;
        this.currentTimeMillisSupplier = currentTimeMillisSupplier;
    }

    @EventListener
    public void handleChainChangedEvent(ChainChanged chainChanged) {
        if (shouldPublish()) {
            publishChanges();
        }
    }

    private boolean shouldPublish() {
        long currentTime = currentTimeMillisSupplier.get();
        return lastTimePublished + publishRateMs < currentTime;
    }

    private void publishChanges() {
        notifyChangesHandler.notifyChanges();
        lastTimePublished = currentTimeMillisSupplier.get();
    }
}
