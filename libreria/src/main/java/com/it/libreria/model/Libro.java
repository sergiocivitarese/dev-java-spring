package com.it.libreria.model;

public abstract class Libro {
    private int id;
    private String titolo;
    private String autore;
    private int annoPubblicazione;
    private boolean disponibile = true;

    //costruttore di default
    public Libro() {
        this.disponibile = true;
    }

    public Libro(int id, String titolo, String autore, int annoPubblicazione) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.disponibile = true;
    }

    @Override
    public String toString() {
        return titolo + " di " + autore + " ( " + annoPubblicazione + ")";
    }

    //getter and setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public String getAutore() { return autore; }
    public void setAutore(String autore) { this.autore = autore; }
    public int getAnnoPubblicazione() { return annoPubblicazione; }
    public void setAnnoPubblicazione(int annoPubblicazione) { this.annoPubblicazione = annoPubblicazione; }
    public boolean isDisponibile() { return this.disponibile; }
    public void setDisponibile(boolean disponibile) { this.disponibile = disponibile; }



}

