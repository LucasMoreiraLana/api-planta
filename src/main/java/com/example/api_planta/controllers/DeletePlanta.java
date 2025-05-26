package com.example.api_planta.controllers;

import com.example.api_planta.dtos.request.RequestDeletePlanta;
import com.example.api_planta.dtos.response.ResponseDeletePlanta;
import com.example.api_planta.dtos.response.ResponseFindPlanta;
import com.example.api_planta.services.DeletePlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/planta")
public class DeletePlanta{

    @Autowired
    private DeletePlantaService deletePlantaService;

    @DeleteMapping("/{plantaId}")
    public ResponseEntity<ResponseDeletePlanta> deletePlanta(@PathVariable String plantaId) {
        try {
            ResponseDeletePlanta response = deletePlantaService.deleteService(plantaId);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
