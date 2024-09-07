package com.example.transactionTracker.repository;

import com.example.transactionTracker.model.Transazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransazioniRepository extends JpaRepository<Transazione, Long> {

    List<Transazione> findByUserIdOrderByDataTransazione(String nome);


}
