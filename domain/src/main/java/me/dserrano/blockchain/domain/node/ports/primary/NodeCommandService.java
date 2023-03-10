package me.dserrano.blockchain.domain.node.ports.primary;

public interface NodeCommandService {
    <T> void process(T command);
}
