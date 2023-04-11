package com.example.pelicula.repositories;

import com.example.pelicula.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository <Movie, Long> {
}
