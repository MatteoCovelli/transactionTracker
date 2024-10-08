package com.example.transactionTracker.service;

import com.example.transactionTracker.model.Transazione;
import com.example.transactionTracker.repository.TransazioniRepository;
import com.example.transactionTracker.utils.Calcoli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
            Calcoli c = Calcoli.builder().build();
            List<String> lista = Arrays.asList(transazioni.split("\n"));

            for (String el : lista) {
                Transazione transazione = Transazione.builder().build();

                if (el.contains("PAGAMENTO POS")) {
                    log.info("PAGAMENTO POS");
                    transazione = c.pagamentoPos(el, nomeUtente);

                } else if (el.contains("STIPENDIO/PENSIONE")) {
                    log.info("STIPENDIO/PENSIONE");
                    transazione = c.stipendio(el, nomeUtente);

                } else if (el.contains("PEDAGGIO AUTOSTRADALE")) {
                    log.info("PEDAGGIO AUTOSTRADALE");
                    transazione = c.pedaggio(el, nomeUtente);

                } else if (el.contains("CANONE MENSILE CONTO")) {
                    log.info("CANONE MENSILE CONTO");
                    transazione = c.canoneMensile(el, nomeUtente);

                } else if (el.contains("ACCREDITO PER RIDUZIONE CANONE")) {
                    log.info("ACCREDITO PER RIDUZIONE CANONE");
                    transazione = c.accreditoCanone(el, nomeUtente);

                } else if (el.contains("RATA POLIZZA")) {
                    log.info("RATA POLIZZA");
                    transazione = c.rataPolizza(el, nomeUtente);

                } else if (el.contains("POSTAGIRO A")) {
                    log.info("POSTAGIRO");
                    transazione = c.postagiro(el, nomeUtente);

                } else if (el.contains("ADDEBITO DIRETTO")) {
                    log.info("ADDEBITO DIRETTO");
                    transazione = c.addebitoDiretto(el, nomeUtente);

                }

                if (transazione.getNomeTransazione() != null) {
                    log.info("SALVO TRANSAZIONE");
                    repository.save(transazione);
                }

            }

        } catch (Exception exception) {
            log.error("ERRORE: Formato dati in input non corretto");
        }
    }
}
