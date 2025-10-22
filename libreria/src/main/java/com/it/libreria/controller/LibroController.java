package com.it.libreria.controller;

import com.it.libreria.model.Libro;
import com.it.libreria.model.LibroCartaceo;
import com.it.libreria.model.LibroDigitale;
import com.it.libreria.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libri")
public class LibroController {
    private final LibroService libroService;


    //constructor injection
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // GET /api/libri
    @GetMapping("/all")
    public ResponseEntity<List<Libro>> getTuttiILibri() {
        List<Libro> libri = libroService.getTuttiILibri();
        return ResponseEntity.ok(libri);
    }

    //GET/api/libri/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Libro> cercaLibroPerId(@PathVariable int id) {
        Libro libro = libroService.cercaLibroPerId(id);
        if(libro == null)
            return ResponseEntity.notFound().build();
        //se tutto ok ritorno
        return ResponseEntity.ok(libro);
    }

    // POST /api/libri
    @PostMapping("/cartaceo")
    public ResponseEntity<String> aggiungiLibroCartaceo(@RequestBody LibroCartaceo libroCartaceo){
        libroService.aggiungiLibro(libroCartaceo);
        return ResponseEntity.ok("Libro cartaceo aggiunto correttamente");
    }

    // POST /api/libri
    @PostMapping("/digitale")
    public ResponseEntity<String> aggiungiLibroDigitale(@RequestBody LibroDigitale libroDigitale){
        libroService.aggiungiLibro(libroDigitale);
        return ResponseEntity.ok("Libro digitale aggiunto correttamente");
    }

    // DELETE /api/libri/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminaLibro(@PathVariable int id) {
        Libro libro = libroService.cercaLibroPerId(id);
        if(libro == null)
            return ResponseEntity.notFound().build();

        libroService.rimuoviLibro(id);
        return ResponseEntity.ok("Libro eliminato correttamente");
    }

}
