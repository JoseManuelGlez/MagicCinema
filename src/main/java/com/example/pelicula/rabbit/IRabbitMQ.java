package com.example.pelicula.rabbit;

import com.example.pelicula.controllers.dtos.responses.CreateMovieResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IRabbitMQ {
    void sendToProductListResponse(List<CreateMovieResponse> responses) throws JsonProcessingException;
}
