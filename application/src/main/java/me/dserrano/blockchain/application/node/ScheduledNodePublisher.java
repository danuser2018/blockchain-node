package me.dserrano.blockchain.application.node;

import me.dserrano.blockchain.application.node.command.PublishNodeCommand;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Component
@EnableScheduling
public class ScheduledNodePublisher {
    private final NodeQueryService nodeQueryService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final Supplier<LocalDateTime> nowSupplier;

    public ScheduledNodePublisher(
            NodeQueryService nodeQueryService,
            ApplicationEventPublisher applicationEventPublisher,
            Supplier<LocalDateTime> nowSupplier
    ) {
        this.nodeQueryService = nodeQueryService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.nowSupplier = nowSupplier;
    }

    @Scheduled(fixedRateString = "${blockchain.nodes.self.publish-rate-ms}")
    public void publishSelfNode() {
        Node selfNode = nodeQueryService.getSelfNode();
        applicationEventPublisher.publishEvent(
                PublishNodeCommand.builder()
                        .node(selfNode)
                        .dateTime(nowSupplier.get())
                        .build()
        );
    }
}
