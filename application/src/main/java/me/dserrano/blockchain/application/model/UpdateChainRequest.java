package me.dserrano.blockchain.application.model;

import lombok.Builder;
import me.dserrano.blockchain.domain.model.Node;

import java.time.LocalDateTime;

@Builder
public record UpdateChainRequest(Node node, LocalDateTime dateTime) {}
