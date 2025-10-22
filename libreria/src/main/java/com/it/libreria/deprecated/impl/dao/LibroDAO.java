package com.it.libreria.deprecated.impl.dao;

import com.it.libreria.model.Libro;

import java.util.List;

public interface LibroDAO {
    void aggiungiLibro(Libro libro);
    List<Libro> getTutti();
    Libro cercaPerId(int id);
    void rimuoviLibro(int id);

}
