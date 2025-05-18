package com.marwane.server.controllers;

import com.marwane.server.dtos.compte.CompteCourantDto;
import com.marwane.server.dtos.compte.CompteDto;
import com.marwane.server.dtos.compte.CompteEpargneDto;
import com.marwane.server.models.AccountStatus;
import com.marwane.server.service.compte.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping("/courant")
    public CompteCourantDto createCompteCourant(@RequestBody CompteCourantDto dto) {
        return compteService.createCompteCourant(dto);
    }

    @PostMapping("/epargne")
    public CompteEpargneDto createCompteEpargne(@RequestBody CompteEpargneDto dto) {
        return compteService.createCompteEpargne(dto);
    }

    @PostMapping("/status")
    public CompteDto changeCompteStatus(@RequestParam String code, @RequestParam AccountStatus status) {

        return compteService.changeCompteStatus(status, code);
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
