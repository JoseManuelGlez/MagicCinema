package com.example.pelicula.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateMovieRequest {
    private double price;
    private String synopsis;
    private String name;
    private String image;
}
