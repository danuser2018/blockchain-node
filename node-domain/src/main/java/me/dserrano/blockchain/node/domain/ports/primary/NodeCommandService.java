package me.dserrano.blockchain.node.domain.ports.primary;

public interface NodeCommandService {
    <T> void process(T command);
}
