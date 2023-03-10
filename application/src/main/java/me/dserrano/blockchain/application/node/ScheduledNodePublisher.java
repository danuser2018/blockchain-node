package me.dserrano.blockchain.application.node;

import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.domain.node.command.PublishNodeCommand;
import me.dserrano.blockchain.domain.node.ports.primary.NodeCommandService;
import me.dserrano.blockchain.domain.node.ports.primary.NodeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Component
@EnableScheduling
public class ScheduledNodePublisher {
    private final NodeQueryService nodeQueryService;
    private final NodeCommandService nodeCommandService;
    private final Supplier<LocalDateTime> nowSupplier;

    @Autowired
    public ScheduledNodePublisher(
            NodeQueryService nodeQueryService,
            NodeCommandService nodeCommandService,
            Supplier<LocalDateTime> nowSupplier
    ) {
        this.nodeQueryService = nodeQueryService;
        this.nodeCommandService = nodeCommandService;
        this.nowSupplier = nowSupplier;
    }

    @Scheduled(fixedRateString = "${blockchain.nodes.self.publish-rate-ms}")
    public void publishSelfNode() {
        Node selfNode = nodeQueryService.getSelfNode();
        nodeCommandService.process(PublishNodeCommand.builder()
                .node(selfNode)
                .dateTime(nowSupplier.get())
                .build()
        );
    }
}
