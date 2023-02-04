package me.dserrano.blockchain.infra.h2.node.mapper;

import me.dserrano.blockchain.infra.h2.node.model.NodeEntity;
import me.dserrano.blockchain.node.domain.model.Node;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface NodeEntityMapper {
    @Mapping(source = "node.id", target = "id")
    @Mapping(source = "node.host", target = "host")
    @Mapping(source = "node.port", target = "port")
    @Mapping(source = "dateTime", target = "dateTime")
    NodeEntity toNodeEntity(Node node, LocalDateTime dateTime);
}
