package com.example.transactionTracker.controller;


import com.example.transactionTracker.model.Transazione;

import com.example.transactionTracker.service.TransazioniServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/transazioni")
@RequiredArgsConstructor
public class TransazioniController {

    private static final Logger log = LoggerFactory.getLogger(TransazioniController.class);
    @Autowired
    private final TransazioniServiceImpl service;

    @GetMapping("/home")
    public String showHome(Model theModel) {
        return "home";
    }

    @GetMapping("/ottieniTutte")
    public String getAll(Authentication authentication, Model theModel) {
        List<Transazione> transazioni = service.findByUserIdOrderByDataTransazione(authentication.getName());
        theModel.addAttribute("transazioni", transazioni);

        return "home";
    }

    @PostMapping("/aggiungiTransazioni")
    public String addTransazioni(@RequestParam String strTransazioni, Authentication authentication) {
        log.info(strTransazioni);
        service.addTransazioni(strTransazioni, authentication.getName());

        return "home";
    }

    @PostMapping("/addTransazioniFromFile")
    public String addTransazioniFromFile(@RequestParam("file") MultipartFile file, Authentication authentication) {

        try {
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.length() - 4);

            if (!file.isEmpty() && ".txt".equals(extension)) {
                String content = new String(file.getBytes(), StandardCharsets.UTF_8);
                service.addTransazioni(content, authentication.getName());
            } else {
                log.info("ESTENSIONE " + extension);
                log.error("ERRORE: Il file caricato è vuoto oppure non è del formato corretto");
            }
        } catch (Exception e) {
            log.error("ERRORE UPLOAD FILE");
        }

        return "home";
    }

}
