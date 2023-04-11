package com.example.pelicula.rabbit;

import com.example.pelicula.controllers.dtos.responses.BaseResponse;
import com.example.pelicula.controllers.dtos.responses.CreateMovieResponse;
import com.example.pelicula.utilities.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitMQ implements IRabbitMQ{

    @Autowired
    private RabbitTemplate template;

    @Override
    public void sendToProductListResponse(List<CreateMovieResponse> responses) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(responses);
        template.convertAndSend("queue.product_list_response");
    }
}
