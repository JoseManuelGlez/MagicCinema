package com.example.pelicula.services.interfaces;

import com.example.pelicula.controllers.dtos.requests.CreateMovieRequest;
import com.example.pelicula.controllers.dtos.responses.BaseResponse;
import com.example.pelicula.controllers.dtos.responses.CreateMovieResponse;

import java.util.List;

public interface IMovieService {
    BaseResponse create(CreateMovieRequest request);

    List<CreateMovieResponse> list();

    void delete(Long id);
}
