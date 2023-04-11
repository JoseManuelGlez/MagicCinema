package com.example.pelicula.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateMovieResponse {
    private Long id;
    private double price;
    private String synopsis;
    private String name;
    private String image;
}
