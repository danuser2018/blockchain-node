package me.dserrano.blockchain.infra.kafka.node.mapper;

import me.dserrano.blockchain.application.node.command.UpdateNodeCommand;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import me.dserrano.blockchain.domain.node.model.Node;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface NodeEventMapper {
    @Mapping(source = "node.id", target = "id")
    @Mapping(source = "node.host", target = "host")
    @Mapping(source = "node.port", target = "port")
    @Mapping(source = "dateTime", target = "dateTime")
    NodeEvent toNodeEvent(Node node, LocalDateTime dateTime);

    @Mapping(source = "id", target = "node.id")
    @Mapping(source = "host", target = "node.host")
    @Mapping(source = "port", target = "node.port")
    @Mapping(source = "dateTime", target = "dateTime")
    UpdateNodeCommand toUpdateNodeCommand(NodeEvent event);
}
