package me.dserrano.blockchain.node.domain.model;

import java.util.UUID;

public class NodeMother {
    public static final String uuid = "6405c233-3208-428d-ad8e-9cf17f164d85";
    public static final String host = "localhost";
    public static final int port = 8080;

    public static final Node node = Node.builder()
            .id(uuid)
            .host(host)
            .port(port)
            .build();
}
