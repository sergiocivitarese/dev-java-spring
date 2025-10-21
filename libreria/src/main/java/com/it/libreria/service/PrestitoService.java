package com.it.libreria.service;

import com.it.libreria.dao.LibroDAO;
import com.it.libreria.dao.PrestitoDAO;
import com.it.libreria.dao.UtenteDAO;
import com.it.libreria.model.Libro;
import com.it.libreria.model.Prestito;
import com.it.libreria.model.Utente;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestitoService {
    private final PrestitoDAO prestitoDAO;
    private final LibroDAO libroDAO;
    private final UtenteDAO utenteDAO;

    public PrestitoService(PrestitoDAO prestitoDAO, LibroDAO libroDAO, UtenteDAO utenteDAO) {
        this.prestitoDAO = prestitoDAO;
        this.libroDAO = libroDAO;
        this.utenteDAO = utenteDAO;
    }

    public void prestaLibro(int idLibro, int idUtente, LocalDate dataInzio, LocalDate dataFine) {
        Libro libro = libroDAO.cercaPerId(idLibro);
        Utente utente = utenteDAO.cercaUtentePerId(idUtente);

        if(libro == null || utente == null) {
            throw new IllegalArgumentException("Libro o utente non trovato");
        }

        //se il libro non è disponibile
        if(!libro.isDisponibile())  //questa mi genera errore
            throw new IllegalStateException("Il libro richiesto non è disponibile");

        //se non genero eccezioni posso elaborare il prestito
        libro.setDisponibile(false);
        int newIdPrestito = prestitoDAO.getTutti().size() + 1; //calcolo l'id del nuovo prestito
        Prestito prestito = new Prestito(newIdPrestito, libro, utente, dataInzio, dataFine);
        prestitoDAO.aggiungiPrestito(prestito);
    }

    //elimina prestito --> si genera con la restituzione del libro
    public void restituisciLibro(int idPrestito) {
        Prestito prestito = prestitoDAO.cercaPerId(idPrestito);
        if(prestito == null) {
            throw new IllegalArgumentException("prestito non trovato");
        }

        if(prestito.isRestituito()){
            throw new IllegalStateException("il libro è gia stato restituito");
        }
        //se non genero nessuna eccezione restituisco
        prestito.restituisci();
        prestitoDAO.rimuoviPrestito(idPrestito);
    }

    public List<Prestito> getTuttiIPrestiti() {
        return prestitoDAO.getTutti();
    }

    public Prestito cercaPrestitoPerId(int id) {
        return prestitoDAO.cercaPerId(id);
    }

}

