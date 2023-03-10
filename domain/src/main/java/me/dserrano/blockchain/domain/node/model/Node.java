package me.dserrano.blockchain.domain.node.model;

import lombok.Builder;

@Builder
public record Node(String id, String host, int port) {}
