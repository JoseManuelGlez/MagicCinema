package com.example.pelicula.controllers.dtos;

import com.example.pelicula.controllers.dtos.requests.CreateMovieRequest;
import com.example.pelicula.controllers.dtos.responses.BaseResponse;
import com.example.pelicula.controllers.dtos.responses.CreateMovieResponse;
import com.example.pelicula.rabbit.IRabbitMQ;
import com.example.pelicula.services.interfaces.IMovieService;
import com.example.pelicula.utilities.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private IMovieService service;

    @Autowired
    private IRabbitMQ rabbitMQ;

    @PostMapping()
    public BaseResponse create(@RequestBody CreateMovieRequest request) {
        return service.create(request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RabbitListener(queues="queue.movie")
    public void list(String payload) throws JsonProcessingException {
        CreateMovieRequest request = MapperUtil.deserialize(payload, CreateMovieRequest.class);
        List<CreateMovieResponse> responses = service.list();
        rabbitMQ.sendToProductListResponse(responses);
    }
}
