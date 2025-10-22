package com.it.libreria.service;

import com.it.libreria.model.Utente;
import com.it.libreria.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }
    @Transactional
    public void aggiungiUtente(Utente utente) {
        utenteRepository.save(utente);
    }
    @Transactional
    public void rimuoviUtente(int id) {
        utenteRepository.deleteById(id);
    }

    public Utente cercaUtentePerId(int id){
        return utenteRepository.findById(id).orElse(null);
    }

    public List<Utente> getTuttiGliUtenti() {
        return utenteRepository.findAll();
    }

}
