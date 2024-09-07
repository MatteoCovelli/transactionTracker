package com.example.transactionTracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "transazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transazione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transazioni")
    @SequenceGenerator(name = "seq_transazioni", sequenceName = "seq_transazioni", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_transazione")
    private String nomeTransazione;

    @Column(name = "data_transazione")
    private Date dataTransazione;

    @Column(name = "importo")
    private float importo;

    @Column(name = "user_id")
    private String userId;

}