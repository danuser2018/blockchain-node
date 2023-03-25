package me.dserrano.blockchain.infra.kafka.node.mapper;

import me.dserrano.blockchain.application.event.UpdateChainRequestReceived;
import me.dserrano.blockchain.domain.node.model.Node;
import me.dserrano.blockchain.infra.kafka.node.model.NodeEvent;
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

    @Mapping(source = "id", target = "updateChainRequest.node.id")
    @Mapping(source = "host", target = "updateChainRequest.node.host")
    @Mapping(source = "port", target = "updateChainRequest.node.port")
    @Mapping(source = "dateTime", target = "updateChainRequest.dateTime")
    UpdateChainRequestReceived toUpdateChainRequestReceived(NodeEvent event);
}
