package com.example.transactionTracker.service;

import com.example.transactionTracker.model.Transazione;

import java.util.List;

public interface TransazioniService {

    public List<Transazione> getAll();

    List<Transazione> findByUserIdOrderByDataTransazione(String nome);

    public void addTransazioni(String transazioni, String nomeUtente);

}
