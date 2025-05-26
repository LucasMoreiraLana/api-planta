package com.example.api_planta.services;


import com.example.api_planta.dtos.response.ResponseFindPlanta;
import com.example.api_planta.repositories.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FindPlantaByIdService {

    @Autowired
    PlantaRepository plantaRepository;

    public ResponseFindPlanta findById(String plantaId) {
        return plantaRepository.findById(plantaId)
                .map(planta -> new ResponseFindPlanta(planta.getPlantaId(), planta.getDescription()))
                .orElseThrow(() -> new NoSuchElementException("Planta com ID " + plantaId + " não encontrada."));
    }
}
