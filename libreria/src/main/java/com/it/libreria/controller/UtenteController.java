package com.it.libreria.controller;

import com.it.libreria.model.Utente;
import com.it.libreria.service.UtenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/utenti")
public class UtenteController {
    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    //GET /api/utenti/all
    @GetMapping("/all")
    public ResponseEntity<List<Utente>> getTuttiGliUtenti(){
        List<Utente> utenti = utenteService.getTuttiGliUtenti();
        return ResponseEntity.ok(utenti);
    }

    //GET /api/utenti/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Utente> CercaUtentePerId(@PathVariable int id) {
        Utente utente = utenteService.cercaUtentePerId(id);
        if(utente == null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(utente);
    }


    //POST /api/utenti
    @PostMapping
    public ResponseEntity<String> aggiungiUtente(@RequestBody Utente utente) {
        utenteService.aggiungiUtente(utente);
        return ResponseEntity.ok("Utente aggiunto correttamente");
    }

    //DELETE /api/utenti/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminaUtente(@PathVariable int id) {
        Utente utente = utenteService.cercaUtentePerId(id);
        if(utente == null){
            return ResponseEntity.notFound().build();
        }
        utenteService.rimuoviUtente(id);
        return ResponseEntity.ok("Utente eliminato correttamente");
    }

}
