package me.dserrano.blockchain.application.model;

import java.time.LocalDateTime;

public class UpdateChainRequestMother {
    public static final LocalDateTime dateTime = LocalDateTime.of(2022, 12, 31, 0, 0);

    public static UpdateChainRequest updateChainRequest = UpdateChainRequest.builder()
            .node(NodeMother.node)
            .dateTime(dateTime)
            .build();
}
