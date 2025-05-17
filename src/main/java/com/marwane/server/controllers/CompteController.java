package com.marwane.server.controllers;

import com.marwane.server.dtos.compte.CompteCourantDto;
import com.marwane.server.dtos.compte.CompteDto;
import com.marwane.server.dtos.compte.CompteEpargneDto;
import com.marwane.server.service.compte.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    @PostMapping("/courant")
    public CompteCourantDto createCompteCourant(@RequestBody CompteCourantDto dto) {
        return compteService.createCompteCourant(dto);
    }

    @PostMapping("/epargne")
    public CompteEpargneDto createCompteEpargne(@RequestBody CompteEpargneDto dto) {
        return compteService.createCompteEpargne(dto);
    }

    @GetMapping("/{code}")
    public CompteDto getCompteByCode(@PathVariable String code) {
        return compteService.getCompteByCode(code);
    }

    @GetMapping("/client/{clientId}")
    public List<CompteDto> getComptesByClient(@PathVariable Long clientId) {
        return compteService.getComptesByClient(clientId);
    }

    @GetMapping
    public List<CompteDto> getAllComptes() {
        return compteService.getAllComptes();
    }

    @DeleteMapping("/{code}")
    public void deleteCompte(@PathVariable String code) {
        compteService.deleteCompte(code);
    }
}
