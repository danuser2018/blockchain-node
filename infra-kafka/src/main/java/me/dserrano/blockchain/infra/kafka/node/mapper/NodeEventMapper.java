package me.dserrano.blockchain.infra.kafka.node.mapper;

import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import me.dserrano.blockchain.node.domain.model.Node;
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
}
