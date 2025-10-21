package com.it.libreria.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroCartaceo extends Libro {

    private int numeroPagine;
    public LibroCartaceo(int id, String titolo, String autore, int annoPubblicazione, int numeroPagine) {
        super(id, titolo, autore, annoPubblicazione);
        this.numeroPagine = numeroPagine;
    }

    public int getNumeroPagine() {return this.numeroPagine; }
    public void setNumeroPagine(int numeroPagine){this.numeroPagine = numeroPagine;}


    @Override
    public String toString(){
        return super.toString() + " [cartaceo, pagine = " + numeroPagine + "]";
    }
}
