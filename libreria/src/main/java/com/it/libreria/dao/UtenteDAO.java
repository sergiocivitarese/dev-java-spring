package com.it.libreria.dao;

import com.it.libreria.model.Utente;

import java.util.List;

public interface UtenteDAO {
    void aggiungiUtente(Utente utente);
    List<Utente> getTutti();
    Utente cercaUtentePerId(int id);
    void rimuoviUtente(int id);



}
