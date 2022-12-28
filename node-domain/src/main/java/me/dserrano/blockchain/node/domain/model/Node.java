package me.dserrano.blockchain.node.domain.model;

import lombok.Builder;

@Builder
public record Node(String id, String host, int port) {}
