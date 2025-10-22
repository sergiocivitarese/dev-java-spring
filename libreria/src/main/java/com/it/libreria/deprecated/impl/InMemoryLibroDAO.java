package com.it.libreria.deprecated.impl;

import com.it.libreria.deprecated.impl.dao.LibroDAO;
import com.it.libreria.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryLibroDAO implements LibroDAO {

    //private final List<Libro> libri = new ArrayList<>();
    private final Map<Integer, Libro> libri = new HashMap<>();

    @Override
    public void aggiungiLibro(Libro libro) {
        libri.put(libro.getId(), libro);
    }

    @Override
    public List<Libro> getTutti() {
        return new ArrayList<>(libri.values());
    }

    @Override
    public Libro cercaPerId(int id) {
        return libri.get(id);
    }

    @Override
    public void rimuoviLibro(int id) {
        if(!libri.containsKey(id))
            throw new IllegalArgumentException("Il libro non esiste, impossibile rimuovere");
        else
            libri.remove(id);
    }
}
