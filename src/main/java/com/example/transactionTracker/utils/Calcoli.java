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

    public float getAddebitoFromString(String noSpazi, String descrizione) {
        return Float.parseFloat(noSpazi.substring(22, noSpazi.indexOf(descrizione) - 1).trim().replace(".","").replace(",","."));
    }

    public float getAccreditoFromString(String noSpazi, String descrizione){

        return Float.parseFloat(noSpazi.substring(21, noSpazi.indexOf(descrizione)).trim().replace(".","").replace(",","."));


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
                .addebito(getAddebitoFromString(noSpazi, "PAGAMENTO POS"))
                .userId(nomeUtente)
                .build();
    }


    public Transazione stipendio(String el, String nomeUtente) {

        String noSpazi = el.replaceAll(" +", " ").trim();

        return Transazione.builder()
                .nomeTransazione("STIPENDIO")
                .dataTransazione(getDataFromString(noSpazi))
                .accredito(getAccreditoFromString(noSpazi, "STIPENDIO/PENSIONE"))
                .userId(nomeUtente)
                .build();
    }

    public Transazione pedaggio(String el, String nomeUtente) {

        String noSpazi = el.replaceAll(" +", " ").trim();
        String nomeTransazione = noSpazi.substring(noSpazi.indexOf("PEDAGGIO AUTOSTRADALE")).trim();
        nomeTransazione = nomeTransazione.substring(38, nomeTransazione.indexOf("ITA OPERAZIONE")).trim();

        return Transazione.builder()
                .nomeTransazione("PEDAGGIO AUTOSTRADALE " + nomeTransazione)
                .dataTransazione(getDataFromString(noSpazi))
                .addebito(getAddebitoFromString(noSpazi, "PEDAGGIO AUTOSTRADALE"))
                .userId(nomeUtente)
                .build();
    }

    public Transazione canoneMensile(String el, String nomeUtente) {
        String noSpazi = el.replaceAll(" +", " ").trim();
        String nomeTransazione = noSpazi.substring(noSpazi.indexOf("CANONE MENSILE CONTO")).trim();
        nomeTransazione = nomeTransazione.substring(nomeTransazione.indexOf("DI")+2).trim();

        return Transazione.builder()
                .nomeTransazione("CANONE MENSILE " + nomeTransazione)
                .dataTransazione(getDataFromString(noSpazi))
                .addebito(getAddebitoFromString(noSpazi, "CANONE MENSILE CONTO"))
                .userId(nomeUtente)
                .build();
    }

    public Transazione accreditoCanone(String el, String nomeUtente) {
        String noSpazi = el.replaceAll(" +", " ").trim();
        String nomeTransazione = noSpazi.substring(noSpazi.indexOf("ACCREDITO PER RIDUZIONE CANONE")).trim();
        nomeTransazione = nomeTransazione.substring(nomeTransazione.indexOf("AL PERIODO DI")+13).trim();

        return Transazione.builder()
                .nomeTransazione("ACCREDITO PER RIDUZIONE CANONE " + nomeTransazione)
                .dataTransazione(getDataFromString(noSpazi))
                .accredito(getAccreditoFromString(noSpazi, "ACCREDITO PER RIDUZIONE CANONE"))
                .userId(nomeUtente)
                .build();

    }

    public Transazione rataPolizza(String el, String nomeUtente) {

        String noSpazi = el.replaceAll(" +", " ").trim();

        return Transazione.builder()
                .nomeTransazione("RATA POLIZZA")
                .dataTransazione(getDataFromString(noSpazi))
                .addebito(getAddebitoFromString(noSpazi, "RATA POLIZZA"))
                .userId(nomeUtente)
                .build();

    }







}
