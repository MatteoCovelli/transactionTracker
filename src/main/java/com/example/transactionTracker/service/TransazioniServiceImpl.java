package com.example.transactionTracker.service;

import com.example.transactionTracker.model.Transazione;
import com.example.transactionTracker.repository.TransazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransazioniServiceImpl implements TransazioniService {

    @Autowired
    private TransazioniRepository repository;


    @Override
    public List<Transazione> getAll() {
        return repository.findAll();
    }
}
