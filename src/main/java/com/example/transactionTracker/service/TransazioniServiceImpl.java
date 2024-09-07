package com.example.transactionTracker.service;

import com.example.transactionTracker.model.Transazione;
import com.example.transactionTracker.repository.TransazioniRepository;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TransazioniServiceImpl implements TransazioniService {

    private static final Logger log = LoggerFactory.getLogger(TransazioniServiceImpl.class);

    @Autowired
    private TransazioniRepository repository;


    @Override
    public List<Transazione> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Transazione> findByUserIdOrderByDataTransazione(String nome) {
        return repository.findByUserIdOrderByDataTransazione(nome);
    }

    @Override
    public void addTransazioni(String transazioni, String nomeUtente) {

        try {
            List<String> lista = Arrays.asList(transazioni.split("\n"));

            //NOME---------------------
            String nomeTransazione = "";
            float addebito;
            String imp = "";
            String strData = "";
            String noSpazi = "";
            Date data = Date.from(Instant.now());

            for (String el : lista) {
                if (el.contains("PAGAMENTO POS")) {

                    noSpazi = el.replaceAll(" +", " ").trim();
                    nomeTransazione = noSpazi.substring(noSpazi.indexOf("POS")).trim();
                    nomeTransazione = nomeTransazione.substring(20, nomeTransazione.indexOf("ITA OPERAZIONE")).trim();

                    addebito = Float.parseFloat(noSpazi.substring(22, noSpazi.indexOf("PAGAMENTO POS") - 1).trim().replace(",", "."));

                    strData = noSpazi.substring(0, 10).trim().replaceAll("/", "-");
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        data = formato.parse(strData);
                    } catch (ParseException e) {
                        System.out.println("Errore durante il parsing della data");
                        e.printStackTrace();
                    }

                    log.info(nomeTransazione);
                    log.info(addebito + "");
                    log.info(data + "");

                    Transazione transazione = Transazione.builder()
                            .nomeTransazione(nomeTransazione)
                            .dataTransazione(data)
                            .importo(addebito)
                            .userId(nomeUtente)
                            .build();

                    repository.save(transazione);

                }
            }

        } catch (Exception exception) {
            log.error("ERRORE: Formato dati in input non corretto");
        }

    }

}
