package com.it.libreria.service;

import com.it.libreria.dao.UtenteDAO;
import com.it.libreria.model.Utente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    private final UtenteDAO utenteDAO;

    public UtenteService(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    public void aggiungiUtente(Utente utente) {
        utenteDAO.aggiungiUtente(utente);
    }

    public void rimuoviUtente(int id) {
        utenteDAO.rimuoviUtente(id) ;
    }

    public Utente cercaUtentePerId(int id){
        return utenteDAO.cercaUtentePerId(id);
    }

    public List<Utente> getTuttiGliUtenti() {
        return utenteDAO.getTutti();
    }

}
