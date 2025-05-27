package com.example.api_planta.services;

import com.example.api_planta.dtos.request.RequestUpdatePlanta;
import com.example.api_planta.models.Planta;
import com.example.api_planta.repositories.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class UpdatePlantaService {

    @Autowired
    private PlantaRepository plantaRepository;

    public void updatePlanta(RequestUpdatePlanta request) {
        Planta planta = plantaRepository.findById(request.plantaId())
                .orElseThrow(() -> new NoSuchElementException("Planta com ID " + request.plantaId() + " não encontrada."));

        planta.setDescription(request.description());
        plantaRepository.save(planta);
    }
}
