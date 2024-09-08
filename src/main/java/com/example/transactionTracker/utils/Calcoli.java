package com.example.transactionTracker.utils;

import com.example.transactionTracker.model.Transazione;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Builder
public class Calcoli {

    private static final Logger log = LoggerFactory.getLogger(Calcoli.class);


    public Date getDataFromString(String noSpazi) {
        String strData = "";
        Date data = Date.from(Instant.now());

        strData = noSpazi.substring(0, 10).trim().replaceAll("/", "-");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        try {
            data = formato.parse(strData);
        } catch (ParseException e) {
            log.error("Errore durante il parsing della data");
            e.printStackTrace();
        }

        return data;
    }

    public float getAddebitoFromString(String noSpazi) {
        return Float.parseFloat(noSpazi.substring(22, noSpazi.indexOf("PAGAMENTO POS") - 1).trim().replace(",", "."));
    }

    public Transazione pagamentoPos(String el, String nomeUtente) {
        String nomeTransazione = "";
        String noSpazi = "";

        noSpazi = el.replaceAll(" +", " ").trim();
        nomeTransazione = noSpazi.substring(noSpazi.indexOf("POS")).trim();
        nomeTransazione = nomeTransazione.substring(20, nomeTransazione.indexOf("ITA OPERAZIONE")).trim();

        return Transazione.builder()
                .nomeTransazione(nomeTransazione)
                .dataTransazione(getDataFromString(noSpazi))
                .addebito(getAddebitoFromString(noSpazi))
                .userId(nomeUtente)
                .build();
    }


//    public Transazione stipendio(String el, String nomeUtente) {
//
//
//    }


}
