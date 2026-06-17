package com.example.demo.service;

import com.example.demo.dto.PokemonDto;
import com.example.demo.mapper.PokemonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private static final Pattern POKEMON_NAME_PATTERN = Pattern.compile("^[a-z0-9-]+$");

    private final PokeApiClient client;
    private final PokemonMapper mapper;

    @Cacheable(value = "pokemon", key = "T(com.example.demo.service.PokemonService).normalizeName(#name)")
    public PokemonDto getPokemon(String name) {
        String normalizedName = normalizeName(name);
        String response = client.getPokemon(normalizedName);

        return mapper.map(response);
    }

    public static String normalizeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Pokemon name is required.");
        }

        String normalizedName = name.trim().toLowerCase(Locale.ROOT);
        if (!POKEMON_NAME_PATTERN.matcher(normalizedName).matches()) {
            throw new IllegalArgumentException("Pokemon name can contain only letters, numbers, and hyphens.");
        }

        return normalizedName;
    }
}
