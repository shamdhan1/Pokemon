package com.example.demo.mapper;

import com.example.demo.dto.PokemonDto;
import com.example.demo.dto.StatDto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class PokemonMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    public PokemonDto map(String json) {
        try {
            JsonNode node = mapper.readTree(json);

            return PokemonDto.builder()
                    .id(node.path("id").asLong())
                    .name(node.path("name").asText())
                    .height(node.path("height").asInt())
                    .weight(node.path("weight").asInt())
                    .baseExperience(node.path("base_experience").asInt())
                    .imageUrl(node.path("sprites")
                            .path("other")
                            .path("official-artwork")
                            .path("front_default")
                            .asText(null))
                    .types(readNamedChildren(node.path("types"), "type"))
                    .abilities(readNamedChildren(node.path("abilities"), "ability"))
                    .stats(readStats(node.path("stats")))
                    .build();
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to parse Pokemon response.", ex);
        }
    }

    private List<String> readNamedChildren(JsonNode items, String childName) {
        List<String> values = new ArrayList<>();
        items.forEach(item -> values.add(item.path(childName).path("name").asText()));
        return values;
    }

    private List<StatDto> readStats(JsonNode statsNode) {
        List<StatDto> stats = new ArrayList<>();
        statsNode.forEach(stat -> stats.add(StatDto.builder()
                .name(stat.path("stat").path("name").asText())
                .value(stat.path("base_stat").asInt())
                .build()));
        return stats;
    }
}
