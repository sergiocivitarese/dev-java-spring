package com.it.libreria.service;

import com.it.libreria.model.Libro;
import com.it.libreria.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    @Transactional
    public void aggiungiLibro(Libro libro) {
        libroRepository.save(libro);
    }

    public Libro cercaLibroPerId(int id) {
        return libroRepository.findById(id).orElse(null);
    }

    public List<Libro> getTuttiILibri() {
        return libroRepository.findAll();
    }
    @Transactional
    public void rimuoviLibro(int id) {
        libroRepository.deleteById(id);
    }


}