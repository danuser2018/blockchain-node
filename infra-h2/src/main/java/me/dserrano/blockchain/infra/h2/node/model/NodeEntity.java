package me.dserrano.blockchain.infra.h2.node.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "NODES")
public record NodeEntity(
        @Id
        @Column(name = "ID", nullable = false)
        String id,
        @Column(name = "HOST", nullable = false)
        String host,
        @Column(name = "PORT", nullable = false)
        int port,
        @Column(name = "DATE_TIME", nullable = false)
        LocalDateTime dateTime
) {}
