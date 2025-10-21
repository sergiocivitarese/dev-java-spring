package com.it.libreria.service;

import com.it.libreria.dao.LibroDAO;
import com.it.libreria.model.Libro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroDAO libroDAO;

    public LibroService(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public void aggiungiLibro(Libro libro) {
        libroDAO.aggiungiLibro(libro);
    }

    public Libro cercaLibroPerId(int id) {
        return libroDAO.cercaPerId(id);
    }

    public List<Libro> getTuttiILibri() {
        return libroDAO.getTutti();
    }

    public void rimuoviLibro(int id) {
        libroDAO.rimuoviLibro(id);
    }


}