package me.dserrano.blockchain.infra.kafka.node.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NodeEvent(String id, String host, int port, LocalDateTime dateTime) {}
