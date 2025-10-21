package com.it.libreria.dao;

import com.it.libreria.model.Prestito;

import java.util.List;

public interface PrestitoDAO {
    void aggiungiPrestito(Prestito prestito);
    List<Prestito> getTutti();
    Prestito cercaPerId(int id);
    void rimuoviPrestito(int id);
}
