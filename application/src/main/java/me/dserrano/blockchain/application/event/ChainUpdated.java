package me.dserrano.blockchain.application.event;

import lombok.Builder;

@Builder
public record ChainUpdated() implements ChainChanged {}
