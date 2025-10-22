package com.it.libreria.controller;

import com.it.libreria.model.Prestito;
import com.it.libreria.service.PrestitoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/prestiti")
public class PrestitoController {
    private final PrestitoService prestitoService;

    public PrestitoController(PrestitoService prestitoService) {
        this.prestitoService = prestitoService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Prestito>> getTuttiIPrestiti(){
        List<Prestito> prestiti = prestitoService.getTuttiIPrestiti();
        return ResponseEntity.ok(prestiti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestito> cercaPrestitoPerId(@PathVariable int id) {
        Prestito prestito = prestitoService.cercaPrestitoPerId(id);
        if(prestito == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(prestito);
    }
    @Transactional
    @PostMapping
    public ResponseEntity<String> prestaLibro(@RequestBody Map<String, String> payload) {
        int idLibro = Integer.parseInt(payload.get("idLibro"));
        int idUtente = Integer.parseInt(payload.get("idUtente"));
        LocalDate dataInizio = LocalDate.parse(payload.get("dataInizio"));
        LocalDate dataFine = LocalDate.parse(payload.get("dataFine"));
        prestitoService.prestaLibro(idLibro, idUtente, dataInizio, dataFine);
        return ResponseEntity.ok("Prestito creato correttamente");
    }
    @Transactional
    @PutMapping("/restituisci/{id}")
    public ResponseEntity<String> restituisciLibro(@PathVariable int id) {
        Prestito prestito = prestitoService.cercaPrestitoPerId(id);

        if(prestito == null)
            throw new IllegalArgumentException("prestito non trovato");
        else if(prestito != null && prestito.isRestituito())
            throw new IllegalStateException("Errore, il libro è già stato restituito");
        else if (prestito != null && prestito.isRestituito() == false)
        {
            //sono nella condizione in cui posso restituire il libro ed annullare il prestito
            prestitoService.restituisciLibro(id);
            return ResponseEntity.ok("Restituzione avvenuta con successo");
        }
        else throw new RuntimeException("restituzione terminata con errore");
    }

}
