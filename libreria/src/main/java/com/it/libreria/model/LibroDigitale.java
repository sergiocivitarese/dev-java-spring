package com.it.libreria.model;

public class LibroDigitale extends Libro {
    private double dimensioneFileMB;


    public LibroDigitale(int id, String titolo, String autore, int annoPubblicazione, double dimensioneFileMB) {
        super(id, titolo, autore, annoPubblicazione);
        this.dimensioneFileMB = dimensioneFileMB;
    }

    public double getDimensioneFileMB() { return this.dimensioneFileMB; }
}
