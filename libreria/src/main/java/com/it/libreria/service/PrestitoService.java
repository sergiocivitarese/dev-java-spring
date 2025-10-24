package com.it.libreria.service;

import com.it.libreria.model.Libro;
import com.it.libreria.model.Prestito;
import com.it.libreria.model.Utente;
import com.it.libreria.repository.LibroRepository;
import com.it.libreria.repository.PrestitoRepository;
import com.it.libreria.repository.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestitoService {
    private final LibroRepository libroRepository;
    private final UtenteRepository utenteRepository;
    private final PrestitoRepository prestitoRepository;

    public PrestitoService(LibroRepository libroRepository, UtenteRepository utenteRepository, PrestitoRepository prestitoRepository) {
        this.libroRepository = libroRepository;
        this.utenteRepository = utenteRepository;
        this.prestitoRepository = prestitoRepository;
    }

    @Transactional
    public void prestaLibro(int idLibro, int idUtente, LocalDate dataInzio, LocalDate dataFine) {
        Libro libro = libroRepository.findById(idLibro).orElseThrow( () -> new EntityNotFoundException("Libro con ID "+ idLibro + " non trovato"));
        Utente utente = utenteRepository.findById(idUtente).orElseThrow( () -> new EntityNotFoundException("Utente con ID "+ idUtente + " non trovato"));

        if(libro == null || utente == null) {
            throw new IllegalStateException("Libro o utente non trovato");
        }

        //se il libro non è disponibile
        if(!libro.isDisponibile())  //questa mi genera errore
            throw new IllegalStateException("Il libro richiesto non è disponibile");

        //se non genero eccezioni posso elaborare il prestito
        libro.setDisponibile(false);
        // l'id del nuovo prestito viene calcolato in modo automatico da jpa per tanto non lo passo
        Prestito prestito = new Prestito(libro, utente, dataInzio, dataFine);
        prestitoRepository.save(prestito);
    }

    //elimina prestito --> si genera con la restituzione del libro
    @Transactional
    public void restituisciLibro(int idPrestito) {
        Prestito prestito = prestitoRepository.findById(idPrestito).orElseThrow( () -> new EntityNotFoundException("Prestito con ID "+ idPrestito + " non trovato"));
        if(prestito == null) {
            throw new IllegalStateException("prestito non trovato");
        }

        if(prestito.isRestituito()) {
            throw new IllegalStateException("il libro è gia stato restituito");
        }
        //se non genero nessuna eccezione restituisco
        prestito.restituisci();
        prestitoRepository.deleteById(idPrestito);
    }

    public List<Prestito> getTuttiIPrestiti() {
        return prestitoRepository.findAll();
    }

    public Prestito cercaPrestitoPerId(int id) {
        return prestitoRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Prestito con ID "+ id + " non trovato"));
    }

}

