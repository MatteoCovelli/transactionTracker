package com.example.transactionTracker.controller;


import com.example.transactionTracker.model.Transazione;

import com.example.transactionTracker.service.TransazioniServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transazioni")
@RequiredArgsConstructor
public class TransazioniController {

    @Autowired
    private final TransazioniServiceImpl service;



    @GetMapping("/ottieniTutte")
    public List<Transazione> getAll(){
        return service.getAll();
    }



}
