package com.example.transactionTracker.mapper;

import com.example.transactionTracker.dto.TransazioniDTO;
import com.example.transactionTracker.model.Transazione;

public class TransazioneMapper {

    public Transazione toEntity(TransazioniDTO transazioniDTO){
        return Transazione.builder()
                .nomeTransazione(transazioniDTO.getNomeTransazione())
                .dataTransazione(transazioniDTO.getDataTransazione())
                .addebito(transazioniDTO.getAddebito())
                .userId(transazioniDTO.getUserId())
                .build();
    }

}
