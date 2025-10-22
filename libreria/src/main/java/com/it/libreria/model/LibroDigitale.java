package com.it.libreria.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DIGITALE")
public class LibroDigitale extends Libro {

    private double dimensioneFileMB;

    public LibroDigitale() {}
    public LibroDigitale(int id, String titolo, String autore, int annoPubblicazione, double dimensioneFileMB) {
        super(id, titolo, autore, annoPubblicazione);
        this.dimensioneFileMB = dimensioneFileMB;
    }

    public double getDimensioneFileMB() { return this.dimensioneFileMB; }
}
