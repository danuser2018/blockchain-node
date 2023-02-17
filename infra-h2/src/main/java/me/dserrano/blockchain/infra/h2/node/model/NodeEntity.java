package me.dserrano.blockchain.infra.h2.node.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NODES")
public class NodeEntity {
        @Id
        @Column(name = "ID", nullable = false)
        private String id;
        @Column(name = "HOST", nullable = false)
        private String host;
        @Column(name = "PORT", nullable = false)
        private int port;
        @Column(name = "DATE_TIME", nullable = false)
        private LocalDateTime dateTime;
}
