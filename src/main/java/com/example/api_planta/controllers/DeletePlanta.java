package com.example.api_planta.controllers;

import com.example.api_planta.dtos.response.ResponseDeletePlanta;
import com.example.api_planta.services.DeletePlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/v1/planta")
public class DeletePlanta {

    @Autowired
    private DeletePlantaService deletePlantaService;

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
