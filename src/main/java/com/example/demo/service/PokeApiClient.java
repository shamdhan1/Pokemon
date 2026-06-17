package com.example.demo.service;

import com.example.demo.exception.PokeApiException;
import com.example.demo.exception.PokemonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@Service
public class PokeApiClient {

    private final RestClient restClient;

    public PokeApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getPokemon(String name) {
        try {
            return restClient.get()
                    .uri("pokemon/{name}", name)
                    .retrieve()
                    .body(String.class);

        } catch (RestClientResponseException ex) {

            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new PokemonNotFoundException("Pokemon '" + name + "' was not found.");

            }
            throw new PokeApiException("PokeAPI request failed with status " + ex.getStatusCode().value() + ".");
        } catch (RestClientException ex) {
            throw new PokeApiException("PokeAPI is currently unreachable.");
        }
    }
}
