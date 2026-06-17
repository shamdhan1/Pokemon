package com.example.demo.controller;

import com.example.demo.dto.PokemonDto;
import com.example.demo.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PokemonController {

    private final PokemonService service;

    @GetMapping("/{name}")
    public PokemonDto getPokemon(@PathVariable String name) {
        return service.getPokemon(name);
    }

    @GetMapping
    public PokemonDto searchPokemon(@RequestParam String name) {
        return service.getPokemon(name);
    }
}
