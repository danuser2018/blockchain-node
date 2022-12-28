package me.dserrano.blockchain.node.domain.model.command;

import lombok.Builder;
import me.dserrano.blockchain.node.domain.model.Node;
import java.time.LocalDateTime;

@Builder
public record PublishNodeCommand(Node node, LocalDateTime dateTime) {}
