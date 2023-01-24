package com.erifas.backend.resource.controller;

import com.erifas.backend.persistence.model.Bilhete;

import org.springframework.http.HttpStatus;
import com.erifas.backend.service.BilheteService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bilhete")
public class BilheteController {
    private final BilheteService bilheteService;

    public BilheteController(BilheteService bilheteService) {
        this.bilheteService = bilheteService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Bilhete> cadastrarBilhete(@RequestBody Bilhete e, @PathVariable Long idRifa) {
        if (bilheteService.verificaCountMaximoBilhetes(idRifa)) {
            return ResponseEntity.ok(bilheteService.save(e));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/ganhadores")
    public ResponseEntity<List<Bilhete>> registrarGanhadores(@RequestBody List<Bilhete> bilhetes) {
        for (Bilhete bilhete : bilhetes) {
            bilhete.setSorteado(true);
        }

        return ResponseEntity.ok(bilheteService.saveAll(bilhetes));
    }

    @GetMapping("/contabilhetes/{id}")
    public ResponseEntity<Integer> contaBilhetesVendidos(@PathVariable Long id) {
        return bilheteService.getCountBilhetes(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
