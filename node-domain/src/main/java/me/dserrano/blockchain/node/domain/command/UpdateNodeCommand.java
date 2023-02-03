package me.dserrano.blockchain.node.domain.command;

import lombok.Builder;
import me.dserrano.blockchain.node.domain.model.Node;

import java.time.LocalDateTime;

@Builder
public record UpdateNodeCommand(Node node, LocalDateTime dateTime) {}
