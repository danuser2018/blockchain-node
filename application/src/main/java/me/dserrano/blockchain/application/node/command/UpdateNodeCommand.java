package me.dserrano.blockchain.application.node.command;

import lombok.Builder;
import me.dserrano.blockchain.domain.node.model.Node;

import java.time.LocalDateTime;

@Builder
public record UpdateNodeCommand(Node node, LocalDateTime dateTime) {}
