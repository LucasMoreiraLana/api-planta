package com.example.api_planta.controllers;

import com.example.api_planta.dtos.response.ResponseFindPlanta;
import com.example.api_planta.services.FindAllPlantasService;
import com.example.api_planta.services.FindPlantaByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/planta")
public class GetFindAllPlanta {

    @Autowired
    private FindAllPlantasService findAllPlantasService;

    @GetMapping("/all")
    public ResponseEntity<List<ResponseFindPlanta>> findAll() {
        try {
            List<ResponseFindPlanta> response = findAllPlantasService.findAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
