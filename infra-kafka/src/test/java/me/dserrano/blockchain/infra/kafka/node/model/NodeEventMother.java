package me.dserrano.blockchain.infra.kafka.node.model;

import me.dserrano.blockchain.application.model.UpdateChainRequest;
import me.dserrano.blockchain.domain.model.Node;

import java.time.LocalDateTime;

public class NodeEventMother {
    public static final String uuid = "6405c233-3208-428d-ad8e-9cf17f164d85";
    public static final String host = "localhost";
    public static final int port = 8080;
    public static final LocalDateTime dateTime = LocalDateTime.of(2022, 12, 31, 0, 0);

    public static final NodeEvent nodeEvent = NodeEvent.builder()
            .id(uuid)
            .host(host)
            .port(port)
            .dateTime(dateTime)
            .build();

    public static final Node node = Node.builder()
            .id(uuid)
            .host(host)
            .port(port)
            .build();

    public static final UpdateChainRequest updateChainRequest = UpdateChainRequest.builder()
            .node(node)
            .dateTime(dateTime)
            .build();
}
