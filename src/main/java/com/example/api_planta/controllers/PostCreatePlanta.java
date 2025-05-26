package com.example.api_planta.controllers;

import com.example.api_planta.dtos.request.RequestCreatePlanta;
import com.example.api_planta.dtos.response.ResponseCreatePlanta;
import com.example.api_planta.services.CreatePlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/planta")
public class PostCreatePlanta {

    @Autowired
    CreatePlantaService createPlantaService;

    @PostMapping("/create")
    public ResponseCreatePlanta createPlanta(@RequestBody RequestCreatePlanta requestCreatePlanta) {
        return createPlantaService.createPlanta(requestCreatePlanta);
    }
}
