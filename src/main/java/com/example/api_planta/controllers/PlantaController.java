package com.example.api_planta.controllers;

import com.example.api_planta.dtos.request.RequestCreatePlanta;
import com.example.api_planta.dtos.request.RequestUpdatePlanta;
import com.example.api_planta.dtos.response.ResponseCreatePlanta;
import com.example.api_planta.dtos.response.ResponseDeletePlanta;
import com.example.api_planta.dtos.response.ResponseFindPlanta;
import com.example.api_planta.services.CreatePlantaService;
import com.example.api_planta.services.DeletePlantaService;
import com.example.api_planta.services.FindAllPlantasService;
import com.example.api_planta.services.FindPlantaByIdService;
import com.example.api_planta.services.UpdatePlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/planta")
public class PlantaController {

    @Autowired
    private CreatePlantaService createPlantaService;

    @Autowired
    private UpdatePlantaService updatePlantaService;

    @Autowired
    private DeletePlantaService deletePlantaService;

    @Autowired
    private FindPlantaByIdService findPlantaByIdService;

    @Autowired
    private FindAllPlantasService findAllPlantasService;

    @PostMapping("/create")
    public ResponseEntity<ResponseCreatePlanta> createPlanta(@RequestBody RequestCreatePlanta requestCreatePlanta) {
        try {
            ResponseCreatePlanta response = createPlantaService.createPlanta(requestCreatePlanta);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

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

    @GetMapping("/{plantaId}")
    public ResponseEntity<ResponseFindPlanta> findById(@PathVariable String plantaId) {
        try {
            ResponseFindPlanta response = findPlantaByIdService.findById(plantaId);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseFindPlanta>> findAll() {
        try {
            List<ResponseFindPlanta> response = findAllPlantasService.findAll();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{plantaId}")
    public ResponseEntity<ResponseDeletePlanta> deletePlanta(@PathVariable String plantaId) {
        try {
            ResponseDeletePlanta response = deletePlantaService.deleteService(plantaId);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}