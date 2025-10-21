package com.it.libreria.model;

import java.time.LocalDate;

public class Prestito {
    private int id;
    private Libro libro;
    private Utente utente;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private boolean restituito;

    public Prestito() { }

    public Prestito(int id, Libro libro, Utente utente, LocalDate dataInizio, LocalDate dataFine) {
        this.id = id;
        this.libro = libro;
        this.utente = utente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.restituito = false;
        if (libro != null) libro.setDisponibile(false);
    }

    //getter and setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }
    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }
    public LocalDate getDataInizio() { return dataInizio; }
    public void setDataInizio(LocalDate dataInizio) { this.dataInizio = dataInizio; }
    public LocalDate getDataFine() { return dataFine; }
    public void setDataFine(LocalDate dataFine) { this.dataFine = dataFine; }
    public boolean isRestituito() { return restituito; }

    //restituisci
    public void restituisci() {
        if(restituito) {
            throw new IllegalStateException("Il libro è già stato restituito!");
        }
        //se non è stato già restituito lo restituisco e imposto il flag a true
        restituito = true;

        //se la restituzione ha avuto successo lo rendo nuovamente disponibile per altri prestiti
        if(libro != null ){
            libro.setDisponibile(true);
        }
    }

    @Override
    public String toString() {
        return "Prestito: " + (libro != null ? libro.getTitolo() : "null") + " a " +
                (utente != null ? utente.getNome() : "null") + (restituito ? " (restituito)" : " (in corso)");
    }

}
