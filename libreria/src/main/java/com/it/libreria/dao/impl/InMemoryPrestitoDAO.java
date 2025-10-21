package com.it.libreria.dao.impl;

import com.it.libreria.dao.PrestitoDAO;
import com.it.libreria.model.Prestito;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryPrestitoDAO implements PrestitoDAO {

    private final Map<Integer, Prestito> prestiti = new HashMap<>();

    @Override
    public void aggiungiPrestito(Prestito prestito) {
        prestiti.put(prestito.getId(), prestito);
    }

    @Override
    public List<Prestito> getTutti() {
        return new ArrayList<>(prestiti.values());
    }

    @Override
    public Prestito cercaPerId(int id) {
        return prestiti.get(id);  //relazione chiave valore estraggo per chiave
    }

    @Override
    public void rimuoviPrestito(int id) {
        prestiti.remove(id);
    }
}
