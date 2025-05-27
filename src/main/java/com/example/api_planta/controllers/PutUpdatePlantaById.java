package com.example.api_planta.controllers;

import com.example.api_planta.dtos.request.RequestUpdatePlanta;
import com.example.api_planta.services.UpdatePlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/planta")
public class PutUpdatePlantaById {

    @Autowired
    private UpdatePlantaService updatePlantaService;

    @PutMapping("/{plantaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updatePlanta(@PathVariable String plantaId, @RequestBody RequestUpdatePlanta request) {
        try {
            RequestUpdatePlanta updatedRequest = new RequestUpdatePlanta(plantaId, request.description());
            updatePlantaService.updatePlanta(updatedRequest);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
