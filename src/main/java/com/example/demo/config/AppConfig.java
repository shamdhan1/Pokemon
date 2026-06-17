package com.example.demo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Configuration
public class AppConfig {



    @Bean
    public WebClient pokeApiWebClient(@Value("${pokemon.api.base-url}") String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public CacheManager cacheManager(
            @Value("${pokemon.cache.maximum-size}") long maximumSize,
            @Value("${pokemon.cache.expire-after-write}") Duration expireAfterWrite) {

        CaffeineCacheManager cacheManager = new CaffeineCacheManager("pokemon");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(expireAfterWrite)
                .recordStats());
        return cacheManager;
    }
}
