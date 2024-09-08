package com.example.transactionTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class TransazioniDTO {

    private String nomeTransazione;

    private Date dataTransazione;

    private float addebito;

    private float accredito;

    private String userId;

}
