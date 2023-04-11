package com.example.pelicula.services;

import com.example.pelicula.controllers.dtos.requests.CreateMovieRequest;
import com.example.pelicula.controllers.dtos.responses.BaseResponse;
import com.example.pelicula.controllers.dtos.responses.CreateMovieResponse;
import com.example.pelicula.entities.Movie;
import com.example.pelicula.repositories.IMovieRepository;
import com.example.pelicula.services.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements IMovieService {
    @Autowired
    private IMovieRepository repository;

    @Override
    public BaseResponse create(CreateMovieRequest request) {
        Movie movie = repository.save(from(request));

        return BaseResponse.builder()
                .data(from(movie))
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .message("Movie created")
                .build();
    }

    @Override
    public List<CreateMovieResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    private Movie from(CreateMovieRequest request){
        Movie movie = new Movie();

        movie.setImage(request.getImage());
        movie.setName(request.getName());
        movie.setPrice(request.getPrice());
        movie.setSynopsis(request.getSynopsis());
        return movie;
    }

    private CreateMovieResponse from(Movie movie){
        CreateMovieResponse response = new CreateMovieResponse();

        response.setSynopsis(movie.getSynopsis());
        response.setImage(movie.getImage());
        response.setName(movie.getName());
        response.setPrice(movie.getPrice());
        response.setId(movie.getId());
        return response;
    }

    private Movie findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
