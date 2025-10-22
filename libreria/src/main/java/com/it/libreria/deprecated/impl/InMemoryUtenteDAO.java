package com.it.libreria.deprecated.impl;

import com.it.libreria.deprecated.impl.dao.UtenteDAO;
import com.it.libreria.model.Utente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemoryUtenteDAO implements UtenteDAO {

    private final List<Utente> utenti = new ArrayList<>();
    @Override
    public void aggiungiUtente(Utente utente) {
        utenti.add(utente);
    }

    @Override
    public List<Utente> getTutti() {
        return utenti;
    }

    @Override
    public Utente cercaUtentePerId(int id) {
        return utenti.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void rimuoviUtente(int id) {
        Utente utente = cercaUtentePerId(id);
        if(utenti.contains(utente))
            utenti.remove(utente);
    }
}
