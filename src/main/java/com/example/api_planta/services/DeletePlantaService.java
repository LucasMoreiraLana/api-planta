package com.example.api_planta.services;


import com.example.api_planta.dtos.response.ResponseDeletePlanta;
import com.example.api_planta.repositories.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DeletePlantaService {

    @Autowired
    private PlantaRepository plantaRepository;

    public ResponseDeletePlanta deleteService(String plantaId) {
        if (!plantaRepository.existsById(plantaId)) {
            throw new NoSuchElementException("Planta com ID " + plantaId + " não encontrada.");
        }

        plantaRepository.deleteById(plantaId);

        return new ResponseDeletePlanta("Planta com ID " + plantaId + " foi excluída com sucesso.");
    }
}
