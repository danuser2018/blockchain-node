package me.dserrano.blockchain.infra.kafka.node.mapper;

import lombok.Generated;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
import me.dserrano.blockchain.node.domain.model.command.PublishNodeCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Generated
@Mapper(componentModel = "spring")
public interface NodeEventMapper {
    @Mapping(source="node.id", target="id")
    @Mapping(source="node.host", target="host")
    @Mapping(source="node.port", target="port")
    @Mapping(source="dateTime", target="dateTime")
    NodeEvent toNodeEvent(PublishNodeCommand command);
}
