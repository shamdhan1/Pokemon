# Pokemon Search Application

## Overview

Pokemon Search Application is a full-stack web application developed using Spring Boot and ReactJS. The application allows users to search for Pokémon by name and view detailed information fetched from the PokeAPI.

The backend exposes RESTful APIs, handles caching for improved performance, and manages error handling. The frontend provides a responsive and user-friendly interface for displaying Pokémon information.

## Features

* Search Pokémon by name
* View Pokémon details such as:

  * Name
  * ID
  * Height
  * Weight
  * Types
  * Abilities
  * Base Experience
  * Image
* RESTful API architecture
* Response caching for improved performance
* Exception handling and validation
* Responsive UI

## Technology Stack

### Backend

* Java 17
* Spring Boot 3
* Maven
* RestClient
* Caffeine Cache

### Frontend

* JavaScript
* HTML5
* CSS3


### External API

* PokeAPI (https://pokeapi.co/api/v2)

## Project Structure

Backend:

* Controller Layer
* Service Layer
* Client Layer
* Exception Handling
* Cache Configuration


## API Endpoint

### Search Pokemon

GET /api/pokemon/{name}

Example:

GET /api/pokemon/pikachu

## Running the Backend

```bash
mvn clean install
java -jar target/pokedex-backend-0.0.1-SNAPSHOT.jar
```
 URL:

http://localhost:8080

## Performance Optimizations

* API response caching
* Cache expiration management
* Reduced external API calls
* Efficient error handling


## Author

Shamdhan Charawande

Java Developer
