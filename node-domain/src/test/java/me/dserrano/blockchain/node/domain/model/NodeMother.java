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

    public static final String anotherUuid = "65af8106-43bf-459e-a362-a03f55fab719";
    public static final String anotherHost = "127.0.0.1";
    public static final int anotherPort = 8081;

    public static final Node anotherNode = Node.builder()
            .id(anotherUuid)
            .host(anotherHost)
            .port(anotherPort)
            .build();
}
