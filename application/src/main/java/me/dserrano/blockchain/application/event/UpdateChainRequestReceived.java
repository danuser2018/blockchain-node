package me.dserrano.blockchain.application.event;

import lombok.Builder;
import me.dserrano.blockchain.application.model.UpdateChainRequest;

@Builder
public record UpdateChainRequestReceived(UpdateChainRequest updateChainRequest) {}
