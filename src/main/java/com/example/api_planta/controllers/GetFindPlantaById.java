package com.example.api_planta.controllers;

import com.example.api_planta.dtos.response.ResponseFindPlanta;
import com.example.api_planta.services.FindPlantaByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/planta")
public class GetFindPlantaById {

    @Autowired
    private FindPlantaByIdService findPlantaByIdService;

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
}
